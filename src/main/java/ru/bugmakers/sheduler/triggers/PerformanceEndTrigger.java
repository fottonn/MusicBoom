package ru.bugmakers.sheduler.triggers;

import org.cfg4j.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;
import ru.bugmakers.localpers.service.ActiveEventService;

import java.time.ZoneId;
import java.util.Date;

import static java.time.Instant.now;
import static ru.bugmakers.utils.DateTimeFormatters.DATE_TIME_FORMATTER;

/**
 * Created by Ivan
 */
@Component
public class PerformanceEndTrigger implements Trigger {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceEndTrigger.class);

    private ActiveEventService activeEventService;
    private ConfigurationProvider appConfigProvider;

    @Autowired
    public void setActiveEventService(ActiveEventService activeEventService) {
        this.activeEventService = activeEventService;
    }

    @Autowired
    @Qualifier("appConfigProvider")
    public void setAppConfigProvider(ConfigurationProvider appConfigProvider) {
        this.appConfigProvider = appConfigProvider;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {

        Date nextExecutionTime = null;

        Date lastCompletionTime = triggerContext.lastCompletionTime();
        long silenceTime = 1000 * appConfigProvider.getProperty("event.silence.period", long.class);

        if (lastCompletionTime == null) {
            nextExecutionTime = new Date(now().toEpochMilli() + 5000);
        } else if (activeEventService.isExistEvents()) {
            nextExecutionTime = new Date(lastCompletionTime.getTime() + silenceTime);
        }

        LOGGER.debug("Next execution time: {}", nextExecutionTime != null ? nextExecutionTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DATE_TIME_FORMATTER) : "done");

        return nextExecutionTime;
    }
}
