package mk.ukim.finki.mk.bookandstay_application.jobs;

import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final HousingService housingService;

    public ScheduledTasks(HousingService housingService) {
        this.housingService = housingService;

    }

    @Scheduled(cron = "0 * * * * *")
    public void refreshMaterializedView() {
        this.housingService.refreshMaterializedView();
    }
}