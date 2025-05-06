package mk.ukim.finki.mk.bookandstay_application.events;

import lombok.Getter;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {

    private LocalDateTime time;
    public HostCreatedEvent(Host source) {
        super(source);
        this.time = LocalDateTime.now();
    }

    public HostCreatedEvent(Host source, LocalDateTime time) {
        super(source);
        this.time = time;
    }
}
