package org.example.projectmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String uploadImage(MultipartFile file);
}
