package ru.bugmakers.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.addFilter("encoding-filter", new CharacterEncodingFilter(UTF_8.name(), true))
                .addMappingForUrlPatterns(null, true, "/*");

        servletContext.addFilter("addHeaderToResponse-filter", new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                filterChain.doFilter(request, response);
            }
        }).addMappingForUrlPatterns(null, true, "/*");

        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}
