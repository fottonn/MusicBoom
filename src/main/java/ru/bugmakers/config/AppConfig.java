package ru.bugmakers.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ivan
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "ru.bugmakers", excludeFilters = @Filter(Configuration.class))
//@Import(PersistConfig.class)
//@Import(SecurityConfig.class)
public class AppConfig {


}