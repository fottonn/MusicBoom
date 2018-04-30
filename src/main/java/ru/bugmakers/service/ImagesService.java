package ru.bugmakers.service;

import org.apache.commons.lang3.StringUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.enums.ImageType;
import ru.bugmakers.utils.ImageUtils;
import ru.bugmakers.utils.UuidGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Сохранение аватарки
     *
     * Сохраняются 2 отмасштабированныx изображения: иконка и аватар
     *
     * @param file файл изображения
     * @param rootPath корневой путь для сохранения изображений
     * @return карта с полным путем аватарки и иконки
     */
    public Map<ImageType, String> saveAvatar(MultipartFile file, String rootPath) throws IOException {

        Map<ImageType, String> images = new HashMap<>(2);

        final BufferedImage image = ImageIO.read(file.getInputStream());

        BufferedImage avatar = ImageUtils.resizeImage(image, ImageType.AVATAR);
        BufferedImage icon = ImageUtils.resizeImage(image, ImageType.ICON);

        String avatarName = saveImage(avatar, rootPath);
        String iconName = saveImage(icon, rootPath);

        images.put(ImageType.AVATAR, avatarName);
        images.put(ImageType.ICON, iconName);

        return images;
    }

    /**
     * Сохранение фотографии
     *
     * @param file файл изображения
     * @param rootPath корневой путь для сохранения изображений
     * @return полный путь изображения
     */
    public String savePhoto(MultipartFile file, String rootPath) throws IOException {

        final BufferedImage image = ImageIO.read(file.getInputStream());

        BufferedImage photo;
        if (image.getWidth() > image.getHeight()) {
            photo = ImageUtils.resizeImage(image, ImageType.PHOTO_W);
        } else {
            photo = ImageUtils.resizeImage(image, ImageType.PHOTO_H);
        }
        return saveImage(photo, rootPath);
    }

    private String saveImage(BufferedImage image, String rootPath) throws IOException {
        String fileNameUUID = UuidGenerator.timeBasedUuidGenerate();
        String fileName = fileNameUUID + ".jpg";
        File pathToSave = new File(rootPath);
        File fileToSave = new File(pathToSave, fileName);
        ImageIO.write(image, "jpg", fileToSave);
        return fileName;
    }

}
