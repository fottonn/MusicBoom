package ru.bugmakers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ivan
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.bugmakers")
public class AppConfig {



}