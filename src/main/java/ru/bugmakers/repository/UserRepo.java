package ru.bugmakers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.UserType;

import java.util.List;

/**
 * Created by Ivan
 */
public interface UserRepo extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByVkAuth_SocialId(String socialId);

    User findByFbAuth_SocialId(String socialId);

    User findByGoogleAuth_SocialId(String socialId);

    User findByEmail_Value(String email);

    User findByPhone(String phone);

    boolean existsByEmail_Value(String email);

    boolean existsByLogin(String login);

    boolean existsByPhone(String phone);

    boolean existsByVkAuth_SocialId(String socialId);
    boolean existsByFbAuth_SocialId(String socialId);
    boolean existsByGoogleAuth_SocialId(String socialId);

    List<User> findDistinctByUserTypeAndNicknameContainingIgnoreCase(UserType userType, String nickname);

    Page<User> findAllByUserType(UserType userType, Pageable pageable);

    Page<User> findAllByUserTypeAndCityIgnoreCase(UserType userType, String city, Pageable pageable);

}
