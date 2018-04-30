package ru.bugmakers.enums;

/**
 * Created by Ivan
 */
public enum ImageType {

    ICON(200, 200),
    AVATAR(720, 592),
    PHOTO_W(1080, 720),
    PHOTO_H(720, 1080);

    int width;
    int height;

    ImageType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
