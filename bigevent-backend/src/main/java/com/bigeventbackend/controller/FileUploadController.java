package com.bigeventbackend.controller;

import com.bigeventbackend.pojo.Result;
import com.bigeventbackend.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = null;
        try {
            url = AliOssUtil.uploadFile(filename, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Result.success(url);
    }
}
