package ru.bugmakers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.bugmakers.config.jwt.TokenAuthenticationManager;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationCheckFilter;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationIncludeFilter;

import static ru.bugmakers.enums.Role.ADMIN;
import static ru.bugmakers.enums.Role.ARTIST;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class InternalApiConfig extends WebSecurityConfigurerAdapter {

        private UserDetailsService userDetailsService;
        private TokenAuthenticationManager tokenAuthenticationManager;

        @Autowired
        public void setUserDetailsService(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Autowired
        public void setTokenAuthenticationManager(TokenAuthenticationManager tokenAuthenticationManager) {
            this.tokenAuthenticationManager = tokenAuthenticationManager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //TODO

            http
                    .csrf().disable()
                    .requestMatchers()
                    .antMatchers("/mapi/**", "/webapi/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(ADMIN.name())
                    .antMatchers("/mapi/artist/**").hasRole(ARTIST.name())
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .usernameParameter("id")
                    .passwordParameter("hash_password")
                    .loginPage("/")
                    .loginProcessingUrl("/authentication/web") //адрес, по которому отправляются пароль и логин
                    .failureForwardUrl("/login/error")
                    .defaultSuccessUrl("/login/success")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Bean
        public TokenAuthenticationCheckFilter tokenAuthenticationFilter() {
            TokenAuthenticationCheckFilter tokenAuthenticationCheckFilter = new TokenAuthenticationCheckFilter();
            tokenAuthenticationManager.setUserDetailsService(userDetailsService);
            tokenAuthenticationCheckFilter.setAuthenticationManager(tokenAuthenticationManager);
            return tokenAuthenticationCheckFilter;
        }

    }

    @Configuration
    @Order(2)
    public static class ExternalApiConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .requestMatchers()
                    .antMatchers("/authentication/**", "/registration/**")
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(tokenAuthenticationIncludeFilter(), UsernamePasswordAuthenticationFilter.class);

        }

        @Bean
        public TokenAuthenticationIncludeFilter tokenAuthenticationIncludeFilter() {
            return new TokenAuthenticationIncludeFilter();
        }

    }
}