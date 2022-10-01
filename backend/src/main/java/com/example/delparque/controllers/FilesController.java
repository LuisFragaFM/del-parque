package com.example.delparque.controllers;

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

    public FilesController(FilesStorageService filesStorageService) {
        this.filesStorageService = filesStorageService;
    }

    @PostMapping("uploads/{condominoId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file,
                                                          @PathVariable String condominoId) {
        Map<String, Object> response = new HashMap<>();
        try {
            filesStorageService.save(file, condominoId);

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

    @GetMapping("/filename/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String, String> getFileName(@PathVariable String userId) {
        return Map.of("filename", filesStorageService.getFilenameByUserId(userId));
    }
}
