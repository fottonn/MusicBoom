package ru.bugmakers.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.bugmakers.config.filter.LoggingFilter;

import javax.servlet.ServletContext;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Ivan
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        servletContext.addFilter("logging-filter", new LoggingFilter())
                .addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter("encoding-filter", new CharacterEncodingFilter(UTF_8.name(), true))
                .addMappingForUrlPatterns(null, false, "/*");

    }
}
