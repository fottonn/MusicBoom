package ru.bugmakers.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayrat on 29.01.2018.
 */
public class MultipartFileDto {
    List<MultipartFile> multipartFiles = new ArrayList<>();

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles) {
        this.multipartFiles = multipartFiles;
    }
}
