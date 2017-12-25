package ru.bugmakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.entity.User;
import ru.bugmakers.repository.UserRepo;

/**
 * Created by Ivan
 */
@Service @Qualifier("userSecurityDetailService")
public class UserSecurityDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByLoginIgnoreCase(username);
        if (user == null) {
            //TODO throw нинди бер хата
        }
        return new UserPrincipal(user);
    }
}
