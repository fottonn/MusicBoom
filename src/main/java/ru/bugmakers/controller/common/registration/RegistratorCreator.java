package ru.bugmakers.controller.common.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.bugmakers.enums.SocialProvider;

/**
 * Created by Ivan
 */
@Component
public class RegistratorCreator {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public Registrator getRegistrator() {
        return context.getBeansOfType(Registrator.class).get("mbRegistrator");
    }

    public Registrator getRegistrator(SocialProvider provider) {
        Registrator registrator = null;
        switch (provider) {
            case VK:
                registrator = context.getBeansOfType(Registrator.class).get("vkRegistrator");
                break;
            case FB:
                registrator = context.getBeansOfType(Registrator.class).get("fbRegistrator");
                break;
            case GOOGLE:
                registrator = context.getBeansOfType(Registrator.class).get("googleRegistrator");
                break;
        }
        return registrator;
    }


}
