package ru.bugmakers.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.bugmakers.config.filter.JsonAuthenticationFilter;
import ru.bugmakers.config.jwt.FailureTokenAuthenticationEntryPoint;
import ru.bugmakers.config.jwt.TokenAuthenticationProvider;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationCheckFilter;
import ru.bugmakers.config.jwt.filter.TokenAuthenticationIncludeFilter;
import ru.bugmakers.config.logout.MbLogoutSuccessHandler;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
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

    //===================CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
        private ConfigurationProvider appConfigProvider;

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

        @Autowired
        @Qualifier("appConfigProvider")
        public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
            this.appConfigProvider = appConfigProvider;
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
                    .cors()
                    .and()
                    .anonymous().disable()
                    .requestMatchers()
                    .antMatchers(POST, URLS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(POST, "/webapi/admin/**").hasAuthority(ADMIN.name())
                    .antMatchers(POST, "/webapi/artist/**").hasAuthority(ARTIST.name())
                    .antMatchers(POST, "/webapi/operator/**").hasAuthority(OPERATOR.name())
                    .antMatchers(POST, "/mapi/artist/**").hasAuthority(ARTIST.name())
                    .antMatchers(POST, "/mapi/listener/**").hasAnyAuthority(LISTENER.name(), ARTIST.name())
                    .antMatchers(POST, "/mapi/registereduser/**").hasAnyAuthority(ARTIST.name(), LISTENER.name())
                    .antMatchers(POST, "/logout").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers(POST, "/mapi/map.performers").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers(POST, "/mapi/artist.get").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .antMatchers(POST, "/mapi/transaction").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .and()
                    .requiresChannel().anyRequest().requires(appConfigProvider.getProperty("spring.security.requires.channel", String.class))
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
        private ConfigurationProvider appConfigProvider;

        @Autowired
        public void setProviderManager(ProviderManager providerManager) {
            this.providerManager = providerManager;
        }

        @Autowired
        @Qualifier("appConfigProvider")
        public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
            this.appConfigProvider = appConfigProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .cors()
                    .and()
                    .requestMatchers()
                    .antMatchers(POST, "/authentication")
                    .and()
                    .requiresChannel().anyRequest().requires(appConfigProvider.getProperty("spring.security.requires.channel", String.class))
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
        private ConfigurationProvider appConfigProvider;

        @Autowired
        public void setProviderManager(ProviderManager providerManager) {
            this.providerManager = providerManager;
        }

        @Autowired
        @Qualifier("appConfigProvider")
        public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
            this.appConfigProvider = appConfigProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .cors()
                    .and()
                    .requestMatchers()
                    .antMatchers(GET, "/authentication")
                    .antMatchers(POST, "/registration")
                    .and()
                    .requiresChannel().anyRequest().requires(appConfigProvider.getProperty("spring.security.requires.channel", String.class))
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
    public static class EnterApiConfig extends WebSecurityConfigurerAdapter {

        private ProviderManager providerManager;
        private FailureTokenAuthenticationEntryPoint failureTokenAuthenticationEntryPoint;
        private MbLogoutSuccessHandler mbLogoutSuccessHandler;
        private ConfigurationProvider appConfigProvider;

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

        @Autowired
        @Qualifier("appConfigProvider")
        public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
            this.appConfigProvider = appConfigProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .cors()
                    .and()
                    .anonymous().disable()
                    .requestMatchers()
                    .antMatchers(POST, "/enter")
                    .and()
                    .authorizeRequests()
                    .antMatchers(POST, "/enter").hasAnyAuthority(ARTIST.name(), LISTENER.name(), OPERATOR.name(), ADMIN.name())
                    .and()
                    .requiresChannel().anyRequest().requires(appConfigProvider.getProperty("spring.security.requires.channel", String.class))
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterAfter(tokenAuthenticationIncludeFilter(), SecurityContextPersistenceFilter.class)
                    .addFilterAfter(tokenAuthenticationFilter(), TokenAuthenticationIncludeFilter.class)
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

        @Bean
        public TokenAuthenticationIncludeFilter tokenAuthenticationIncludeFilter() {
            return new TokenAuthenticationIncludeFilter();
        }

    }

    @Configuration
    @Order(5)
    public static class PublicApiConfig extends WebSecurityConfigurerAdapter {

        private ConfigurationProvider appConfigProvider;

        @Autowired
        @Qualifier("appConfigProvider")
        public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
            this.appConfigProvider = appConfigProvider;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .cors()
                    .and()
                    .authorizeRequests().anyRequest().permitAll()
                    .and()
                    .requiresChannel().anyRequest().requires(appConfigProvider.getProperty("spring.security.requires.channel", String.class))
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;

        }
    }
}