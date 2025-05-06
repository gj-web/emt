package mk.ukim.finki.mk.bookandstay_application.service.application;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateHostDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHostDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> save(CreateHostDto host);

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto host);

    void deleteById(Long id);
}
