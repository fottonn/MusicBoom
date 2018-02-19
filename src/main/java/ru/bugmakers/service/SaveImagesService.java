package ru.bugmakers.service;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Ayrat on 25.01.2018.
 */
@Component
public class SaveImagesService {

    String saveFile(MultipartFile file, String rootPath) throws IOException {
        UUID fileNameUUID = Generators.timeBasedGenerator().generate();
        String originalFileName = file.getOriginalFilename();
        String fileName = fileNameUUID.toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        File fileToSave = new File(rootPath, fileName);
        return fileToSave.getAbsolutePath();
    }
}
