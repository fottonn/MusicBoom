package ru.bugmakers.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

/**
 * Created by Ivan
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

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

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
        File uploadDir = new File(System.getProperty("user.dir") + "/temp");
        uploadDir.getParentFile().mkdirs();
        registration.setMultipartConfig(new MultipartConfigElement(
                uploadDir.getAbsolutePath(),
                maxUploadSizeInMb,
                maxUploadSizeInMb * 5,
                maxUploadSizeInMb / 2));
    }

}
