package com.example.delparque.service.impl;

import com.example.delparque.config.Default;
import com.example.delparque.config.EmailSender;
import com.example.delparque.dto.ResetPassword;
import com.example.delparque.exception.DelParqueSystemException;
import com.example.delparque.model.User;
import com.example.delparque.repository.UsersRepository;
import com.example.delparque.service.UsersService;
import com.example.delparque.service.mappers.UserMapper;
import com.example.delparque.tools.Utility;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final JavaMailSender mailSender;
    private final EmailSender emailSender;
    private final Default def;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    UsersServiceImpl(UsersRepository usersRepository,
                     BCryptPasswordEncoder bCryptPasswordEncoder,
                     JavaMailSender mailSender,
                     EmailSender emailSender,
                     Default def) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
        this.emailSender = emailSender;
        this.def = def;
    }

    @Override
    public com.example.delparque.dto.User findById(String id) {
        return UserMapper.entityToDto(usersRepository.findById(id).orElseThrow());
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = usersRepository.findByEmail(email).orElseThrow();

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new UsernameNotFoundException("Wrong credentials");
    }

    @Override
    public void sendMailForRecoverPassword(HttpServletRequest httpServletRequest)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String email = httpServletRequest.getParameter("email");

        User user = usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found"));

        if (user.getResetPasswordToken() == null) {
            String token = UUID.randomUUID().toString();
            user.setResetPasswordToken(token);
            usersRepository.save(user);
        }

        helper.setFrom(emailSender.getUsername(), "Fraccionamiento DelParque");
        helper.setTo(user.getEmail());

        String resetPasswordLink =
                Utility.getSiteURL(httpServletRequest) + "/reset_password?token=" + user.getResetPasswordToken();

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello " + user.getName() + "</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void updatePasswordForRecoverPassword(String token, ResetPassword resetPassword) {
        User user = usersRepository.findByResetPasswordToken(token).orElseThrow();

        user.setPassword(bCryptPasswordEncoder.encode(resetPassword.getNewPassword()));
        user.setResetPasswordToken(null);

        usersRepository.save(user);
    }

    @Override
    public com.example.delparque.dto.User register(com.example.delparque.dto.User user) {

        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
        }

        Optional<User> optionalUser = usersRepository.findByEmail(user.getEmail());
        optionalUser.ifPresent(value -> {
            if (value.getEmail().equals(user.getEmail()) && !user.getId().equals(value.getId())) {
                throw new DelParqueSystemException("email ocupado por otro usuario", "DUPLICATE_EMAIL");
            }
        });

        user.setPassword(bCryptPasswordEncoder.encode(def.getPassword()));

        return UserMapper.entityToDto(usersRepository.save(UserMapper.dtoToEntity(user)));
    }

    @Override
    public com.example.delparque.dto.User buildUserForEmail(String email) {
        User user = usersRepository.findByEmail(email).orElseThrow();

        return com.example.delparque.dto.User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .telephoneNumber(user.getTelephoneNumber())
                .emergencyNumber(user.getEmergencyNumber())
                .name(user.getName())
                .role(user.getRole())
                .picture(user.getPicture())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<com.example.delparque.dto.User> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList());
    }

}
