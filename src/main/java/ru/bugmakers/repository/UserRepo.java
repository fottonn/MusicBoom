package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.User;

import java.util.Optional;

/**
 * Created by Ivan
 */
public interface UserRepo extends JpaRepository<User, Long> {

    User findByLoginIgnoreCase(String login);

    User findByVkAuth_SocialId(String socialId);

    User findByFbAuth_SocialId(String socialId);

    User findByGoogleAuth_SocialId(String socialId);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Optional<User> findById(Long id);
}
