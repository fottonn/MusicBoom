package ru.bugmakers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.User;

/**
 * Created by Ivan
 */
public interface UserRepo extends JpaRepository<User, Long> {

    User findByLoginIgnoreCase(String login);

    User findByVkAuth_SocialId(String socialId);

    User findByEmail(String email);

    //TODO проверить.
    User saveAndFlush(User user);
}
