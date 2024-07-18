package com.sakthi.genericServices.controller;

import com.sakthi.genericServices.model.ResourceSavedResponse;
import com.sakthi.genericServices.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tracker/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/images/{userId}/{resource}/{fileName}")
    public ResponseEntity<ResourceSavedResponse> saveImage(@PathVariable String userId,
                                                           @PathVariable String resource,
                                                           @PathVariable String fileName,
                                                           @RequestParam MultipartFile file
    ) throws IOException {
        return resourceService.saveImage(userId, resource, fileName, file);
    }

    @GetMapping("/images/{userId}/{resource}/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String userId,
                                             @PathVariable String resource,
                                             @PathVariable String fileName
    ){
        return resourceService.getImage(userId, resource, fileName);
    }
}
