package ru.bugmakers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import ru.bugmakers.config.filter.JsonAuthenticationFilter;
import ru.bugmakers.config.jwt.FailureTokenAuthenticationEntryPoint;
import ru.bugmakers.config.jwt.TokenAuthenticationProvider;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationCheckFilter;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationIncludeFilter;
import ru.bugmakers.config.logout.MbLogoutSuccessHandler;

import java.util.Arrays;

import static ru.bugmakers.enums.Role.*;

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

    //====================providers
    @Bean
    public DaoAuthenticationProvider jsonAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public TokenAuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(userDetailsService);
    }

    //====================entry_points
    @Bean
    public FailureTokenAuthenticationEntryPoint failureTokenAuthenticationEntryPoint() {
        return new FailureTokenAuthenticationEntryPoint();
    }

    //====================encoders
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //====================authentication_managers
    @Bean
    public ProviderManager providerManager() {
        return new ProviderManager(Arrays.asList(
                jsonAuthenticationProvider(),
                tokenAuthenticationProvider()
        ));
    }

    @Override
    public void configure(WebSecurity web) {
        final String[] URLS = {
                "/index.jsp",
                "/favicon.ico",
                "/test"
        };
        web
                .debug(false)
                .ignoring().antMatchers(URLS)
        ;
    }

    @Configuration
    @Order(1)
    public static class InternalWebApiConfig extends WebSecurityConfigurerAdapter {

        private ProviderManager providerManager;
        private FailureTokenAuthenticationEntryPoint failureTokenAuthenticationEntryPoint;
        private MbLogoutSuccessHandler mbLogoutSuccessHandler;

        @Autowired
        public void setProviderManager(ProviderManager providerManager) {
            this.providerManager = providerManager;
        }

        @Autowired
        public void setFailureTokenAuthenticationEntryPoint(FailureTokenAuthenticationEntryPoint failureTokenAuthenticationEntryPoint) {
            this.failureTokenAuthenticationEntryPoint = failureTokenAuthenticationEntryPoint;
        }

        @Autowired
        public void setMbLogoutSuccessHandler(MbLogoutSuccessHandler mbLogoutSuccessHandler) {
            this.mbLogoutSuccessHandler = mbLogoutSuccessHandler;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            final String[] URLS = {
                    "/webapi/admin/**",
                    "/webapi/artist/**",
                    "/webapi/operator/**",
                    "/mapi/artist/**",
                    "/mapi/listener/**",
                    "/mapi/registereduser/**",
                    "/mapi/map.performers",
                    "/mapi/artist.get",
                    "/mapi/transaction",
                    "/logout"
            };


            http
                    .csrf().disable()
                    .anonymous().disable()
                    .requestMatchers()
                    .antMatchers(URLS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/webapi/admin/**").hasAuthority(ADMIN.name())
                    .antMatchers("/webapi/artist/**").hasAuthority(ARTIST.name())
                    .antMatchers("/webapi/operator/**").hasAuthority(OPERATOR.name())
                    .antMatchers("/mapi/artist/**").hasAuthority(ARTIST.name())
                    .antMatchers("/mapi/listener/**").hasAuthority(LISTENER.name())
                    .antMatchers("/mapi/registereduser/**").hasAnyAuthority(ARTIST.name(), LISTENER.name())
                    .antMatchers("/logout").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers("/mapi/map.performers").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers("/mapi/artist.get").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers("/mapi/transaction").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterAfter(tokenAuthenticationFilter(), SecurityContextPersistenceFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(failureTokenAuthenticationEntryPoint)
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessHandler(mbLogoutSuccessHandler)
            ;
        }

        @Bean
        public TokenAuthenticationCheckFilter tokenAuthenticationFilter() {
            return new TokenAuthenticationCheckFilter(providerManager);
        }

    }

    @Configuration
    @Order(2)
    public static class JsonAuthenticationApiConfig extends WebSecurityConfigurerAdapter {

        private ProviderManager providerManager;

        @Autowired
        public void setProviderManager(ProviderManager providerManager) {
            this.providerManager = providerManager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .requestMatchers()
                    .antMatchers(HttpMethod.POST, "/authentication")
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterAfter(tokenAuthenticationIncludeFilter(), SecurityContextPersistenceFilter.class)
                    .addFilterAfter(jsonAuthenticationFilter(), TokenAuthenticationIncludeFilter.class)
            ;

        }

        @Bean
        public JsonAuthenticationFilter jsonAuthenticationFilter() {
            return new JsonAuthenticationFilter(providerManager);
        }

        @Bean
        public TokenAuthenticationIncludeFilter tokenAuthenticationIncludeFilter() {
            return new TokenAuthenticationIncludeFilter();
        }

    }

    @Configuration
    @Order(3)
    public static class SocialAuthenticationApiConfig extends WebSecurityConfigurerAdapter {

        private ProviderManager providerManager;

        @Autowired
        public void setProviderManager(ProviderManager providerManager) {
            this.providerManager = providerManager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .requestMatchers()
                    .antMatchers(HttpMethod.GET, "/authentication")
                    .antMatchers(HttpMethod.POST, "/registration")
                    .and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterAfter(tokenAuthenticationIncludeFilter(), SecurityContextPersistenceFilter.class)
            ;
        }

        @Bean
        public JsonAuthenticationFilter jsonAuthenticationFilter() {
            return new JsonAuthenticationFilter(providerManager);
        }

        @Bean
        public TokenAuthenticationIncludeFilter tokenAuthenticationIncludeFilter() {
            return new TokenAuthenticationIncludeFilter();
        }
    }

    @Configuration
    @Order(4)
    public static class PublicApiConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .authorizeRequests().anyRequest().permitAll()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;

        }
    }
}