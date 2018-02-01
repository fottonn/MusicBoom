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
        return context.getBean(MbRegistrator.class);
    }

    public Registrator getRegistrator(SocialProvider provider) {
        Registrator registrator = null;
        switch (provider) {
            case VK:
                registrator = context.getBean(VkRegistrator.class);
                break;
            case FB:
                registrator = context.getBean(FbRegistrator.class);
                break;
            case GOOGLE:
                registrator = context.getBean(GoogleRegistrator.class);
                break;
        }
        return registrator;
    }


}
