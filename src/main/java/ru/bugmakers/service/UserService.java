package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public User saveUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    public User findUserByVkSocialId(String socialId) {
        return userRepo.findByVkAuth_SocialId(socialId);
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findUserByLogin(String login) {
        return userRepo.findByLoginIgnoreCase(login);
    }

    @Transactional
    public User updateUser(User user) {return userRepo.saveAndFlush(user);}

    public Optional<User> findUserById(String id) { return userRepo.findById(Long.valueOf(id));
    }
}
