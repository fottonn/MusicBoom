package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.bugmakers.dto.common.UserDTO;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.repository.UserRepo;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Ivan
 */
@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public User findUserByVkSocialId(String socialId) {
        return userRepo.findByVkAuth_SocialId(socialId);
    }

    public User findUserByFbSocialId(String socialId) {
        return userRepo.findByFbAuth_SocialId(socialId);
    }

    public User findUserByGoogleSocialId(String socialId) {
        return userRepo.findByGoogleAuth_SocialId(socialId);
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail_Value(email.toLowerCase());
    }

    public User findUserByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public User updateUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User findUserByPhone(String phone) {
        return userRepo.findByPhone(phone);
    }

    public boolean isExistsById(Long id) {
        return userRepo.existsById(id);
    }

    public boolean isExistsByEmail(String email) {
        return userRepo.existsByEmail_Value(email.toLowerCase());
    }

    public boolean isExistsByLogin(String login) {
        return userRepo.existsByLogin(login);
    }

    public boolean isExistsByPhone(String phone) {
        return userRepo.existsByPhone(phone);
    }

    /**
     * Поиск всех пользователей, {@code nickname} которых содержит {@code value}, и тип {@code userType}
     *
     * @param userType тип пользователя
     * @param value    поисковое значение
     * @return все пользователи с {@code nickname} LIKE {@code %value%}
     */
    public List<UserDTO> findAllUsersByUserTypeAndByNicknameLikeValue(final UserType userType, final String value) {
        final List<UserDTO> result = new ArrayList<>();
        if (value == null || value.length() < 3) return result;
        final String[] array = Pattern.compile("[\\p{Punct}]").split(value);
        final List<String> values = new ArrayList<>();
        for (String s : array) {
            if (s.length() > 2) values.add(s);
        }
        final Map<Long, User> users = new HashMap<>();
        values.forEach(s -> userRepo.findDistinctByUserTypeAndNicknameContainingIgnoreCase(userType, s).forEach(user -> users.putIfAbsent(user.getId(), user)));
        users.values().forEach(user -> result.add(new UserDTO(String.valueOf(user.getId()), user.getNickname())));
        result.sort(Comparator.comparing(UserDTO::getNickname));
        return result;
    }

    /**
     * Постраничный поиск всех пользователей с типом {@code userType}
     *
     * @param userType тип пользователя
     * @param pageable {@link Pageable}
     * @return страница пользователей с типом {@code userType}
     */
    public Page<User> findAllUsersByUserType(final UserType userType, final Pageable pageable) {
        return userRepo.findAllByUserType(userType, pageable);
    }

    /**
     * Удаление пользователя
     *
     * @param id идентификатор пользователя
     */
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    /**
     * Постраничный поиск всех пользователей из определенного города
     *
     * @param city     город
     * @param pageable {@link Pageable}
     * @return страница пользователей из определенного города
     */
    public Page<User> findAllUsersByCity(final String city, final Pageable pageable) {
        return userRepo.findAllByCityIgnoreCase(city, pageable);
    }
}
