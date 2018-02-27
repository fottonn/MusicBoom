package ru.bugmakers.service;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Ayrat on 25.01.2018.
 */
@Service
public class SaveImagesService {

    String saveFile(MultipartFile file, String rootPath) throws IOException {
        UUID fileNameUUID = Generators.timeBasedGenerator().generate();
        String originalFileName = file.getOriginalFilename();
        Assert.notNull(originalFileName, "OriginalFileName is null");
        String fileName = fileNameUUID.toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        File fileToSave = new File(rootPath, fileName);
        file.transferTo(fileToSave);
        return fileName;
    }

    boolean removeFile(String fileName, String rootPath) {
        File fileToRemove = new File(rootPath, fileName);
        return fileToRemove.delete();
    }
}
