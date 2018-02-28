package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.User;
import ru.bugmakers.repository.UserRepo;

import java.util.Optional;

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
        return userRepo.findByEmail(email);
    }

    public User findUserByLogin(String login) {
        return userRepo.findByLoginIgnoreCase(login);
    }

    public User updateUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public Optional<User> findUserById(String id) {
        return userRepo.findById(Long.valueOf(id));
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
        return userRepo.existsByEmail(email);
    }

    public boolean isExistsByLogin(String login) {
        return userRepo.existsByLogin(login);
    }

    public boolean isExistsByPhone(String phone) {
        return userRepo.existsByPhone(phone);
    }
}
