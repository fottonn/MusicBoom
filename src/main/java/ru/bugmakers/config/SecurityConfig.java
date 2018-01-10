package ru.bugmakers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ru.bugmakers.enums.Role.ROLE_ADMIN;
import static ru.bugmakers.enums.Role.ROLE_ARTIST;

/**
 * Created by Ivan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userSecurityDetailService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO

        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/admin/**").hasRole(ROLE_ADMIN.name())
                .mvcMatchers("/mapi/artist/**").hasRole(ROLE_ARTIST.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("hash_password")
                .loginPage("/login")
                .loginProcessingUrl("/login/process") //адрес, по которому отправляются пароль и логин
                .failureForwardUrl("/login/error")
                .defaultSuccessUrl("/login/success")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}