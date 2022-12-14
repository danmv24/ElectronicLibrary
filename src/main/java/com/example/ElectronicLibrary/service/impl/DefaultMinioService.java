package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.service.MinioService;
import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
public class DefaultMinioService implements MinioService {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.bucket.name}")
    String defaultBucketName;


    @Override
    public void uploadFile(MultipartFile file) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                            .bucket(defaultBucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                    .build());

        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | IOException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void getFile(String filename) {
        try {
            DownloadObjectArgs downloadObjectArgs = DownloadObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(filename)
                    .filename(filename)
                    .build();
            minioClient.downloadObject(downloadObjectArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                            .object(filename)
                            .bucket(defaultBucketName)
                    .build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException |
                 IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
    }


    @PostConstruct
    public void init() {
    }

}
