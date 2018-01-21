package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bugmakers.dto.response.AuthenticationResponse;
import ru.bugmakers.entity.User;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.enums.UserType;
import ru.bugmakers.mappers.User2UserDtoConverter;
import ru.bugmakers.repository.UserRepo;

import java.util.Optional;

/**
 * Created by Ivan
 */
@Service
public class UserAuthenticationService {

    private UserRepo userRepo;
    private User2UserDtoConverter user2UserDtoConverter;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setUser2UserDtoConverter(User2UserDtoConverter user2UserDtoConverter) {
        this.user2UserDtoConverter = user2UserDtoConverter;
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse getResponseByUserType(Long id) {

        if (id == null) return null;
        AuthenticationResponse response = null;

        try {
            Optional<User> userEntity = userRepo.findById(id);

            if (userEntity.isPresent()) {
                User user = userEntity.get();
                UserType userType = user.getUserType();
                if (userType != null) {
                    response = new AuthenticationResponse(RsStatus.SUCCESS);
                    switch (userType) {
                        case ARTIST:
                        case LISTENER:
                            response.setUser(user2UserDtoConverter.convert(user));
                            break;
                        case ADMIN:
                            //TODO возможно лучше вернуть стандартный успешный ответ вместо массива пользователей
                            break;
                        case OPERATOR:
                            break;
                    }

                }
            }
        } catch (Exception e) {
            return null;
        }

        return response;
    }
}
