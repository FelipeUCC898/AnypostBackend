package com.anypost.integration.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Service
public class BlobStorageService {

    private final Path basePath;

    public BlobStorageService(@Value("${storage.local-path:uploads}") String basePath) throws IOException {
        this.basePath = Paths.get(basePath).toAbsolutePath();
        Files.createDirectories(this.basePath);
        log.info("[storage] Using local storage path: {}", this.basePath);
    }

    /** Guarda un archivo y retorna su URL pública simulada */
    public String upload(MultipartFile file, String folder) throws IOException {
        Path targetFolder = basePath.resolve(folder);
        Files.createDirectories(targetFolder);

        Path targetFile = targetFolder.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

        String url = "/media/" + folder + "/" + file.getOriginalFilename();
        log.info("[storage] Saved file: {}", url);
        return url;
    }

    /** Borra un archivo */
    public void delete(String folder, String filename) {
        try {
            Files.deleteIfExists(basePath.resolve(folder).resolve(filename));
            log.info("[storage] Deleted file {}/{}", folder, filename);
        } catch (IOException e) {
            log.error("[storage] Error deleting file {}/{}: {}", folder, filename, e.getMessage());
        }
    }
}