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

    User findByEmail(String email);

    //TODO проверить.
    User saveAndFlush(User user);

    Optional<User> findById(Long id);
}
