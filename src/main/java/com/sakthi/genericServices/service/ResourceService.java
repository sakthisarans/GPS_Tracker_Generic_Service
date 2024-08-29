package com.sakthi.genericServices.service;

import com.sakthi.genericServices.model.ResourceSavedResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Service
public class ResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceService.class);
    @Value("${application.resource.directory}")
    String path;
    @Value("${tracker.gateway.url}")
    String uri;
    public ResponseEntity<ResourceSavedResponse> saveImage( String resourceType,
                                            MultipartFile image) throws IOException {

        ResourceSavedResponse resourceSavedResponse=null;

        Path root = Paths.get(path+String.format("/%s",resourceType.toLowerCase()));
        if(!Files.exists(root)){
            Files.createDirectories(root);
        }
        try {
            String originalFileExt=image.getOriginalFilename().split("\\.")[image.getOriginalFilename().split("\\.").length-1];

            String fileName=generateRandomString(10)+"."+originalFileExt;
            if(Files.exists(root.resolve(fileName))){
                saveImage(resourceType,image);
            }
            Files.copy(image.getInputStream(), root.resolve(fileName));
            resourceSavedResponse= ResourceSavedResponse.builder()
                    .URI(String.format("%s/tracker/resource/images/%s/%s",uri,resourceType,fileName.toLowerCase()))
                    .isSaved(true)
                    .build();
        } catch (Exception e) {
            resourceSavedResponse= ResourceSavedResponse.builder()
                    .URI(null)
                    .isSaved(false)
                    .build();
        }

        return new ResponseEntity<>(resourceSavedResponse, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> getImage(
                                                  String resourceType,
                                                  String fileName){
        Path root = Paths.get(path+String.format("/%s/%s",resourceType,fileName.toLowerCase()));
        try {
            byte[] in = Files.readAllBytes(root);
            return ResponseEntity.ok().contentType(MediaType.MULTIPART_MIXED).body(in);
        }catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
        private String generateRandomString(int length) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            return buffer.toString();
        }
}
