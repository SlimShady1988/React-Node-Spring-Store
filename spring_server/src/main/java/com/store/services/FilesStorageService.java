package com.store.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FilesStorageService {

    private final Path root = Paths
            .get("../React-Node-Store/spring_server/src/main/resources/static"); // Just this strange way is correct.
//    private final Path root = Paths.get("/static/");


    public String save(MultipartFile file) {
        try {
            String fileCopy = UUID.randomUUID() + ".jpg";
            Files.copy(file.getInputStream(), root.resolve(fileCopy));

            return fileCopy;

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

}
