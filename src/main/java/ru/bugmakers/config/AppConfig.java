package ru.bugmakers.config;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.bugmakers.config.logout.MbLogoutSuccessHandler;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ivan
 */
@Configuration
@ComponentScan(value = "ru.bugmakers", excludeFilters = @Filter(Configuration.class))
@Import({SecurityConfig.class, PersistConfig.class, LocalPersistConfig.class})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

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
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(5 * 1024 * 1024); //5Mb
        return resolver;
    }

    @Bean
    public ConfigurationProvider appConfigProvider() throws IOException {
        URI config = new ClassPathResource("app_config.properties").getURI();
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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
    }
}