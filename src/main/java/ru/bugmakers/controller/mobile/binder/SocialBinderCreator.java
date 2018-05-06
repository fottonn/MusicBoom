package ru.bugmakers.controller.mobile.binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.bugmakers.enums.SocialProvider;

@Component
public class SocialBinderCreator {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public SocialBinder getSocialBinder(SocialProvider provider) {

        SocialBinder binder = null;

        switch (provider) {
            case VK:
                binder = context.getBean(VkBinder.class);
                break;
            case FB:
                binder = context.getBean(FbBinder.class);
                break;
            case GOOGLE:
                binder = context.getBean(GoogleBinder.class);
                break;
        }
        return binder;
    }


}
