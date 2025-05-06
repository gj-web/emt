package mk.ukim.finki.mk.bookandstay_application.service.domain;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface HousingService {
    List<Housing> findAll();

    Optional<Housing> save(Housing housing);

    Optional<Housing> findById(Long id);

    Optional<Housing> update(Long id, Housing housing);

    Optional<Housing> markedAsRented(Long id);

    void deleteById(Long id);

    List<Housing> searchWithFilters(String name, Category category, Long hostId, Integer numRooms);

    void refreshMaterializedView();
}
