package ru.bugmakers.config.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.bugmakers.entity.User;

import java.util.Collection;

/**
 * Created by Ivan
 */
public class UserPrincipal implements UserDetails {

    private User artist;

    public UserPrincipal(User artist) {
        this.artist = artist;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return artist.getAuthorities();
    }

    @Override
    public String getPassword() {
        return artist.getPassword();
    }

    @Override
    public String getUsername() {
        //TODO return email
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException();
    }
}
