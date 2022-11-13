package com.example.delparque.controllers;

import com.example.delparque.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class FilesController {

    private final FilesStorageService filesStorageService;

    FilesController(FilesStorageService filesStorageService) {
        this.filesStorageService = filesStorageService;
    }

    @PostMapping("uploads/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          @PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            filesStorageService.save(file, userId);

            response.put("message", "Archivo subido exitosamente");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            String message = "Error al subir el archivo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.singletonMap("message", message));
        }
    }

    @GetMapping("/file/{filename}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUARD', 'ROLE_RESIDENT')")
    public Resource getFile(@PathVariable String filename) {
        return filesStorageService.load(filename);
    }

}
