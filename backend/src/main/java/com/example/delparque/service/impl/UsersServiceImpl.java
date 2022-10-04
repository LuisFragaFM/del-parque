package com.example.delparque.service.impl;

import com.example.delparque.config.Default;
import com.example.delparque.config.EmailSender;
import com.example.delparque.dto.ResetPassword;
import com.example.delparque.exception.DelParqueSystemException;
import com.example.delparque.model.RolesByUser;
import com.example.delparque.model.User;
import com.example.delparque.repository.RolesRepository;
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
    private final RolesRepository rolesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository,
                            RolesRepository rolesRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder,
                            JavaMailSender mailSender,
                            EmailSender emailSender,
                            Default def) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
        this.emailSender = emailSender;
        this.def = def;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        User user = usersRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Wrong credentials"));

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
    public User register(String email, String role, String name) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);

        optionalUser.ifPresent(value -> {
            if (value.getEmail().equals(email)) {
                throw new DelParqueSystemException("email ocupado por otro usuario", "DUPLICATE_EMAIL");
            }
        });

        com.example.delparque.dto.User user = new com.example.delparque.dto.User();

        String userId = UUID.randomUUID().toString();

        user.setId(userId);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(def.getPassword()));
        user.setName(name);

        RolesByUser rolesByUser = new RolesByUser();
        rolesByUser.setUserId(userId);
        rolesByUser.setRole(role);
        rolesByUser.setId(UUID.randomUUID().toString());

        usersRepository.save(UserMapper.dtoToEntity(user));
        rolesRepository.save(rolesByUser);

        return UserMapper.dtoToEntity(user);
    }

    @Override
    public com.example.delparque.dto.User buildUserForEmail(String email) {
        User user = usersRepository.findByEmail(email).orElseThrow();

        return com.example.delparque.dto.User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roles(rolesRepository.findRolesByUser(user.getId()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<com.example.delparque.dto.User> getAllUsers() {
        return usersRepository.findAll().stream().map(UserMapper::entityToDto)
                .peek(user -> user.setRoles(rolesRepository.findRolesByUser(user.getId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> removeRole(String userId, String role) {

        rolesRepository.deleteByUserIdAndRole(userId, role);

        return rolesRepository.findRolesByUser(userId);
    }

    @Override
    @Transactional
    public List<String> addRole(String userId, String role) {
        RolesByUser roleByUser = new RolesByUser();

        roleByUser.setId(UUID.randomUUID().toString());
        roleByUser.setRole(role);
        roleByUser.setUserId(userId);

        rolesRepository.save(roleByUser);

        return rolesRepository.findRolesByUser(userId);
    }
}