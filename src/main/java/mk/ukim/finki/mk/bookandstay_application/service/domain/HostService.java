package mk.ukim.finki.mk.bookandstay_application.service.domain;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> save(Host host);

    Optional<Host> findById(Long id);

    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);

    void refreshMaterializedView();
}
