package mk.ukim.finki.mk.bookandstay_application.listeners;

import mk.ukim.finki.mk.bookandstay_application.events.HostCreatedEvent;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandler {

    private HostService hostService;

    public HostEventHandler(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent hostCreatedEvent) {
        this.hostService.refreshMaterializedView();
    }

}
