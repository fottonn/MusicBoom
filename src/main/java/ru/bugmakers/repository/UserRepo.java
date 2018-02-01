package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.User;

/**
 * Created by Ivan
 */
public interface UserRepo extends JpaRepository<User, Long> {

    User findByLoginIgnoreCase(String login);

    User findByVkAuth_SocialId(String socialId);

    User findByFbAuth_SocialId(String socialId);

    User findByGoogleAuth_SocialId(String socialId);

    User findByEmail(String email);

    User findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    boolean existsByPhone(String phone);
}
