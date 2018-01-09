package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bugmakers.entity.User;
import ru.bugmakers.repository.UserRepo;

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

    public User findUserByEmail(String email) {return userRepo.findUserByEmail(email);}

}
