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

    @PostMapping("/images/{resource}")
    public ResponseEntity<ResourceSavedResponse> saveImage(
                                                           @PathVariable String resource,
                                                           @RequestParam MultipartFile file
    ) throws IOException {
        return resourceService.saveImage(resource, file);
    }

    @GetMapping("/images/{resource}/{fileName}")
    public ResponseEntity<byte[]> getImage(  @PathVariable String resource,
                                             @PathVariable String fileName
    ){
        return resourceService.getImage( resource, fileName);
    }
}
