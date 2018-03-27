package ru.bugmakers.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bugmakers.config.filter.LoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        servletContext.addFilter("addHeaderToResponse-filter", new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                filterChain.doFilter(request, response);
                response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            }
        }).addMappingForUrlPatterns(null, false, "/*");

    }
}
