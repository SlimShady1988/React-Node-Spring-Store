package com.store.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;
@Service
public interface StorageService {

    public void init();

    public String save(MultipartFile file);
//    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

}