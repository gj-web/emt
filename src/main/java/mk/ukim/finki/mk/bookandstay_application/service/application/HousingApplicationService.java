package mk.ukim.finki.mk.bookandstay_application.service.application;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateHousingDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHousingDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface HousingApplicationService {
    List<DisplayHousingDto> findAll();

    Optional<DisplayHousingDto> save(CreateHousingDto housing);

    Optional<DisplayHousingDto> findById(Long id);

    Optional<DisplayHousingDto> update(Long id, CreateHousingDto housing);

    Optional<DisplayHousingDto> markedAsRented(Long id);

    void deleteById(Long id);

    List<DisplayHousingDto> searchWithFilters(String name, Category category, Long hostId, Integer numRooms);
}
