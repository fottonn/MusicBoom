package ru.bugmakers.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.utils.UuidGenerator;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ayrat on 25.01.2018.
 */
@Service
public class SaveImagesService {

    String saveFile(MultipartFile file, String rootPath) throws IOException {
        String fileNameUUID = UuidGenerator.timeBasedUuidGenerate();
        String originalFileName = file.getOriginalFilename();
        Assert.notNull(originalFileName, "OriginalFileName is null");
        String fileName = fileNameUUID + originalFileName.substring(originalFileName.lastIndexOf("."));
        File pathToSave = new File(rootPath);
        File fileToSave = new File(pathToSave, fileName);
        fileToSave.getParentFile().mkdirs();
        file.transferTo(fileToSave);
        return fileName;
    }

    void removeFile(String fileName, String rootPath) {
        File fileToRemove = new File(rootPath, fileName);
        fileToRemove.delete();
    }
}
