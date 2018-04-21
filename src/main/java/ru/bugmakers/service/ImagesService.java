package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ImagesService {

    private ConfigurationProvider appConfigProvider;

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
    }

    public String saveFile(MultipartFile file, String rootPath) throws IOException {
        String fileNameUUID = UuidGenerator.timeBasedUuidGenerate();
        String originalFileName = file.getOriginalFilename();
        Assert.notNull(originalFileName, "OriginalFileName is null");
        String fileName = fileNameUUID + originalFileName.substring(originalFileName.lastIndexOf("."));
        File pathToSave = new File(rootPath);
        File fileToSave = new File(pathToSave, fileName);
        file.transferTo(fileToSave);
        return fileName;
    }

    public void removeFile(String fileName, String rootPath) {
        File fileToRemove = new File(rootPath, fileName);
        fileToRemove.delete();
    }

    public String fullImagePath(String imageName) {
        if (StringUtils.isBlank(imageName)) return null;
        String imageUrl = appConfigProvider.getProperty("url.image.path", String.class);
        return imageUrl + imageName;
    }

}
