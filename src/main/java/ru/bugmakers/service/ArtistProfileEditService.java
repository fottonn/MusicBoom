package ru.bugmakers.service;

import org.apache.commons.collections4.CollectionUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.Photo;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Ayrat on 17.01.2018.
 */
@Service
public class ArtistProfileEditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistProfileEditService.class);

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private UserDTO2UserEnricher userDTO2UserEnricher;
    private ImagesService imagesService;
    private EmailService emailService;
    private ConfigurationProvider appConfigProvider;
    private PhotoService photoService;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDTO2UserEnricher(UserDTO2UserEnricher userDTO2UserEnricher) {
        this.userDTO2UserEnricher = userDTO2UserEnricher;
    }

    @Autowired
    public void setImagesService(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    /**
     * Метод редактирования профиля артиста
     *
     * @param userDTO объект {@link UserDTO}, содержащий изменения профиля артиста
     * @param user    текущий пользователь
     * @throws MbException ошибки при попытке сохранить изменения профиля
     */
    public void artistProfileEdit(UserDTO userDTO, User user) throws MbException {
        if (user == null || userDTO == null) throw MbException.create(MbError.APE01);
        if (user.getUserType() != UserType.ARTIST) throw MbException.create(MbError.APE02);
        userDTO2UserEnricher.enrich(userDTO, user);
        if (user.getEmail() != null && !user.getEmail().isEnabled()) {
            emailService.sendConfirmationEmail(user);
        }
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для сохранения/измененя аватара
     *
     * @param user   текущий пользователь
     * @param avatar файл аватара
     * @throws MbException ошибки при попытке сохранить/изменить аватар
     */
    public String artistAvatarChange(User user, MultipartFile avatar) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        if (avatar == null) throw MbException.create(MbError.APE06);
        String avatarName;
        try {
            avatarName = imagesService.saveAvatar(avatar, appConfigProvider.getProperty("app.image.path", String.class));
            Assert.notNull(avatarName, "Avatar name is null!");
        } catch (Exception e) {
            LOGGER.error("Avatar change failed", e);
            throw MbException.create(MbError.APE04);
        }
        user.setAvatar(avatarName);
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
            return imagesService.fullImagePath(avatarName);
        } catch (Exception e) {
            LOGGER.error("Avatar change failed", e);
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для сохранения/изменения номера телефона
     *
     * @param user        текущий пользователь
     * @param phoneNumber номер телефона
     * @throws MbException ошибки при попытке сохранить/изменить номер телефона
     */
    public void artistPhoneChange(User user, String phoneNumber) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.setPhone(phoneNumber);

        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для изменения пароля
     *
     * @param user        текущий пользователь
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     * @throws MbException ошибки при попытке изменить пароль
     */
    public void artistPasswordChange(User user, String oldPassword, String newPassword) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw MbException.create(MbError.APE05);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для изменения типа деятельности артиста
     *
     * @param user       текущий пользователь
     * @param creativity тип деятельности
     * @throws MbException ошибки при изменении типа деятельности артиста
     */
    public void artistCreativityChange(User user, String creativity) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.getArtistInfo().setCreativity(creativity);
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для изменения инструмента артиста
     *
     * @param user       текущий пользователь
     * @param instrument название инструмента
     * @throws MbException ошибки при изменении инструмента
     */
    public void artistInstrumentChange(User user, String instrument) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.getArtistInfo().setInstrument(instrument);
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для изменения жанра
     *
     * @param user  текущий пользователь
     * @param genre жанр
     * @throws MbException ошибки при изменении жанра
     */
    public void artistGenreChange(User user, String genre) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.getArtistInfo().setGenre(genre);
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод для изменения статуса заказываемости артиста
     *
     * @param user      текущий пользователь
     * @param orderable заказываемость
     * @throws MbException ошибки при смене заказываемости
     */
    public void artistSetOrderableChange(User user, boolean orderable) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.getArtistInfo().setOrderable(orderable);

        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }
    }

    /**
     * Метод удаления фотографий
     *
     * @param user     текущий пользователь
     * @param photoIds набор идентификаторов фотографий
     * @throws MbException ошибки при удалении фотографий
     */
    public void artistDeletePhotos(User user, Set<String> photoIds) throws Exception {
        if (user == null) throw MbException.create(MbError.APE01);
        if (CollectionUtils.isEmpty(photoIds)) return;
        if (CollectionUtils.isNotEmpty(photoIds)) {
            photoIds.forEach(photoId -> imagesService.removeFile(photoId.substring(photoId.lastIndexOf("/") + 1), appConfigProvider.getProperty("app.image.path", String.class)));
            photoService.deletePhotos(photoIds);
        }
    }

    /**
     * Метод загрузки фотографий
     *
     * @param user           текущий пользователь
     * @param multipartFiles набор файлов
     * @throws Exception ошибки при сохранении файлов
     */
    public List<String> artistUploadPhotos(User user, Collection<MultipartFile> multipartFiles) throws Exception {
        if (user == null) throw MbException.create(MbError.APE01);
        List<Photo> photos = new ArrayList<>();
        List<String> photosUrl = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(multipartFiles)) {
            for (MultipartFile multipartFile : multipartFiles) {
                String savedFile = imagesService.savePhoto(multipartFile, appConfigProvider.getProperty("app.image.path", String.class));
                photosUrl.add(imagesService.fullImagePath(savedFile));
                photos.add(new Photo(savedFile, user.getId()));
            }
        }
        photoService.savePhotos(photos);
        return photosUrl;
    }

    private void checkSavedUser(User user) {
        Assert.notNull(user, "SavedUser is null");
    }

    /**
     * Метод удаления аватара
     * @param user текущий пользователь
     */
    public void artistAvatarDelete(User user) {
        imagesService.removeFile(user.getAvatar(), appConfigProvider.getProperty("app.image.path", String.class));
        user.setAvatar(null);
        User updatedUser = userService.updateUser(user);
        checkSavedUser(updatedUser);
    }
}
