package org.example.projectmanagement.service.impl;

import org.example.projectmanagement.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class FileServiceImpl implements IFileService {
    @Value("${path-upload}")
    private String path;
    @Value("8080")
    private Integer port;

    @Override
    public String uploadImage(MultipartFile file) {
        String fileName= file.getOriginalFilename();
        try{
            FileCopyUtils.copy(file.getBytes(),new File(path+fileName));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "http://localhost:"+port+"/"+fileName;
    }
}
