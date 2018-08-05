package ru.bugmakers.utils;

import net.coobird.thumbnailator.Thumbnails;
import ru.bugmakers.enums.ImageType;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Ivan
 */
public class ImageUtils {

    public static BufferedImage resizeImage(BufferedImage image, ImageType imageType) throws IOException {

        BufferedImage resizedImage;

        int width = imageType.getWidth() < image.getWidth() ? imageType.getWidth() : image.getWidth();
        int height = imageType.getHeight() < image.getHeight() ? imageType.getHeight() : image.getHeight();

        if (image.getWidth() != width || image.getHeight() != height) {

            resizedImage = Thumbnails.of(image)
                    .size(width, height)
                    .asBufferedImage();

        } else {
            resizedImage = image;
        }

        return resizedImage;
    }

}
