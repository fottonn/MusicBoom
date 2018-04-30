package ru.bugmakers.config;

import org.springframework.util.Assert;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

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
        int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB
        File uploadDir;
        try {
            uploadDir = new File(getUploadDir());
        } catch (IOException e) {
            throw new RuntimeException("Property 'multipart.temporary.upload.location' had not loaded", e);
        }
        Assert.isTrue(uploadDir.exists(), uploadDir.toString() + " - is not available");
        registration.setMultipartConfig(new MultipartConfigElement(
                uploadDir.getAbsolutePath(),
                maxUploadSizeInMb,
                maxUploadSizeInMb * 5,
                maxUploadSizeInMb / 2));
    }

    private String getUploadDir() throws IOException {
        Properties property = new Properties();
        if ("dev".equalsIgnoreCase(System.getProperty("spring.profiles.active"))) {
            property.load(getClass().getResourceAsStream("/app_config_dev.properties"));
        } else {
            property.load(getClass().getResourceAsStream("/app_config.properties"));
        }

        return property.getProperty("multipart.temporary.upload.location");
    }


}
