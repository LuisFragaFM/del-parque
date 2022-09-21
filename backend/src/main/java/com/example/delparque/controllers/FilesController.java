package com.example.delparque.controllers;

import com.example.delparque.dto.LoggedUser;
import com.example.delparque.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class FilesController {

    private final FilesStorageService filesStorageService;
    private final SessionHelper sessionHelper;

    public FilesController(FilesStorageService filesStorageService, SessionHelper sessionHelper) {
        this.filesStorageService = filesStorageService;
        this.sessionHelper = sessionHelper;
    }

    @PostMapping("uploads")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          Principal principal) {
        Map<String, Object> response = new HashMap<>();
        LoggedUser loggedUser = sessionHelper.getLoggedUser(principal);
        try {
            filesStorageService.save(file, loggedUser.getUserId());

            response.put("message", "Archivo subido exitosamente");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            String message = "Error al subir el archivo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.singletonMap("message", message));
        }
    }

    @GetMapping("/file/{filename}")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageService.load(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG).body(file);
    }

    @GetMapping("/filename")
    public Map<String, String> getFileName(Principal principal) {
        LoggedUser loggedUser = sessionHelper.getLoggedUser(principal);
        return Map.of("filename", filesStorageService.getFilenameByLoggedUser(loggedUser));
    }
}
