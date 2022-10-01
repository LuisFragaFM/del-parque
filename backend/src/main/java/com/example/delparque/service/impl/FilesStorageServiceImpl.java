package com.example.delparque.service.impl;

import com.example.delparque.model.UserImage;
import com.example.delparque.repository.UserImageRepository;
import com.example.delparque.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("uploads");
    private final UserImageRepository userImageRepository;

    public FilesStorageServiceImpl(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String userId) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            String fileName = UUID.randomUUID() + ".jpg";
            Path newFile = this.root.resolve(fileName);
            Files.copy(file.getInputStream(), newFile);

            UserImage userImage = userImageRepository.findByUserId(userId).orElse(new UserImage());

            if (userImage.getId() == null) {
                userImage.setId(UUID.randomUUID().toString());
                userImage.setUserId(userId);
            }

            userImage.setUri("/uploads/" + fileName);
            userImage.setFilename(fileName);

            userImageRepository.save(userImage);

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public String getFilenameByUserId(String userId) {
        return userImageRepository.findByUserId(userId).orElse(new UserImage())
                .getFilename();
    }
}
