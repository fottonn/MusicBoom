package ru.bugmakers.service;

import org.apache.commons.collections4.CollectionUtils;
import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.Genre;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;
import ru.bugmakers.mappers.enrichers.UserDTO2UserEnricher;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Ayrat on 17.01.2018.
 */
@Service
public class ArtistProfileEditService {
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private UserDTO2UserEnricher userDTO2UserEnricher;
    private SaveImagesService saveImagesService;
    private EmailService emailService;
    private ConfigurationProvider appConfigProvider;

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
    public void setSaveImagesService(SaveImagesService saveImagesService) {
        this.saveImagesService = saveImagesService;
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
    public void artistAvatarChange(User user, MultipartFile avatar) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        if (avatar == null) throw MbException.create(MbError.APE06);
        String fileName;
        try {
            fileName = saveImagesService.saveFile(avatar, appConfigProvider.getProperty("app.image.path", String.class));
            Assert.notNull(fileName, "File name is null");
        } catch (Exception e) {
            throw MbException.create(MbError.APE04);
        }
        user.setAvatar(fileName);
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
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
        if (user.getPassword().equals(passwordEncoder.encode(oldPassword))) {
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
        user.getArtistInfo().setGenre(Genre.valueOf(genre.toUpperCase()));
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
    public void artistSetOrderableChange(User user, Boolean orderable) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        user.getArtistInfo().setOrdered(orderable);

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
    public void artistDeletePhotos(User user, Set<String> photoIds) throws MbException {
        if (user == null) throw MbException.create(MbError.APE01);
        if (CollectionUtils.isEmpty(photoIds)) return;
        Set<String> photos = user.getPhotos();
        if (CollectionUtils.isNotEmpty(photoIds)) {
            for (String id : photoIds) {
                boolean deleted = photos.remove(id);
                if (deleted) {
                    saveImagesService.removeFile(id, appConfigProvider.getProperty("app.image.path", String.class));
                }
            }
            photos.removeAll(photoIds);
            try {
                User savedUser = userService.updateUser(user);
                checkSavedUser(savedUser);
            } catch (Exception e) {
                throw MbException.create(MbError.APE03);
            }
        }
    }

    /**
     * Метод загрузки фотографий
     *
     * @param user           текущий пользователь
     * @param multipartFiles набор файлов
     * @throws MbException ошибки при сохранении файлов
     */
    public void artistUploadPhotos(User user, Collection<MultipartFile> multipartFiles) throws MbException, IOException {
        if (user == null) throw MbException.create(MbError.APE01);
        if (CollectionUtils.isNotEmpty(multipartFiles)) {
            for (MultipartFile multipartFile : multipartFiles) {
                String savedFile = saveImagesService.saveFile(multipartFile, appConfigProvider.getProperty("app.image.path", String.class));
                user.getPhotos().add(savedFile);
            }
        }
        try {
            User savedUser = userService.updateUser(user);
            checkSavedUser(savedUser);
        } catch (Exception e) {
            throw MbException.create(MbError.APE03);
        }

    }

    private void checkSavedUser(User user) {
        Assert.notNull(user, "SavedUser is null");
    }
}
