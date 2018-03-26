package ru.bugmakers.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Ivan
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        servletContext.addFilter("logging-filter", new LoggingFilter())
//                .addMappingForUrlPatterns(null, false, "/*");
//
//        servletContext.addFilter("encoding-filter", new CharacterEncodingFilter(UTF_8.name(), true))
//                .addMappingForUrlPatterns(null, false, "/*");
//
//        servletContext.addFilter("addHeaderToResponse-filter", new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//                filterChain.doFilter(request, response);
//                System.out.println("after addHeaderToResponse-filter");
//            }
//        }).addMappingForUrlPatterns(null, false, "/*");
//
//        super.onStartup(servletContext);
//    }

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
