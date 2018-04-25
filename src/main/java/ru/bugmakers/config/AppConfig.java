package ru.bugmakers.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.*;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.bugmakers.config.logout.MbLogoutSuccessHandler;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Ivan
 */
@Configuration
@ComponentScan(value = "ru.bugmakers", excludeFilters = @Filter(Configuration.class))
@Import({SecurityConfig.class, PersistConfig.class, LocalPersistConfig.class, ScheduleConfig.class})
@EnableWebMvc
@EnableAsync
public class AppConfig implements WebMvcConfigurer, AsyncConfigurer{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MbLogoutSuccessHandler mbLogoutSuccessHandler() {
        return new MbLogoutSuccessHandler();
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    @Profile("!dev")
    public ConfigurationProvider appConfigProvider() throws IOException {
        URI config = new ClassPathResource("app_config.properties").getURI();
        ConfigFilesProvider provider = () -> Collections.singletonList(Paths.get(config));
        ConfigurationSource source = new FilesConfigurationSource(provider);
        return new ConfigurationProviderBuilder().withConfigurationSource(source).build();
    }

    @Bean
    @Profile("dev")
    @Qualifier("appConfigProvider")
    public ConfigurationProvider appConfigProviderDev() throws IOException {
        URI config = new ClassPathResource("app_config_dev.properties").getURI();
        ConfigFilesProvider provider = () -> Collections.singletonList(Paths.get(config));
        ConfigurationSource source = new FilesConfigurationSource(provider);
        return new ConfigurationProviderBuilder().withConfigurationSource(source).build();
    }

    @Bean
    public ConfigurationProvider emailConfigProvider() throws IOException {
        URI config = new ClassPathResource("email.properties").getURI();
        ConfigFilesProvider provider = () -> Collections.singletonList(Paths.get(config));
        ConfigurationSource source = new FilesConfigurationSource(provider);
        return new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .build();
    }

    @Override
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

}