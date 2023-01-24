package com.example.ElectronicLibrary.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Configuration
public class MinioConfig {

    @Value("${minio.access.name}")
    String accessKey;
    @Value("${minio.access.secret}")
    String accessSecret;
    @Value("${minio.url}")
    String minioUrl;

    @Value("${minio.bucket.name}")
    String defaultBucketName;


    @Bean
    @Primary
    public MinioClient generateMinioClient() throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = new MinioClient.Builder().credentials(accessKey, accessSecret).endpoint(minioUrl).build();

        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder()
                        .bucket(defaultBucketName)
                .build());

        if(!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                            .bucket(defaultBucketName)
                    .build());
        }

        return minioClient;
    }

}
