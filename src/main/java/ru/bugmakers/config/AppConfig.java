package ru.bugmakers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ivan
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.bugmakers", excludeFilters = @Filter(Configuration.class))
@EnableJpaRepositories(basePackages = "ru.bugmakers.repository", entityManagerFactoryRef = "emf")
@Import({SecurityConfig.class, PersistConfig.class})
public class AppConfig {


}