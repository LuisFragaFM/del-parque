package com.example.delparque.service;

import com.example.delparque.dto.LoggedUser;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    void init();

    void save(MultipartFile file, String userId);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();

    String getFilenameByUserId(String userId);
}
