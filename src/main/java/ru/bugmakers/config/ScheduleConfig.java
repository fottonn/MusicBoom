package ru.bugmakers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by Ivan
 */
@Configuration
public class ScheduleConfig {

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler eventsEndThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("events-end-");
        return scheduler;
    }

}
