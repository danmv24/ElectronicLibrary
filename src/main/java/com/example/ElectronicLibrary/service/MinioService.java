package com.example.ElectronicLibrary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinioService {
    void uploadFile(MultipartFile file);

    void getFile(String filename);
}
