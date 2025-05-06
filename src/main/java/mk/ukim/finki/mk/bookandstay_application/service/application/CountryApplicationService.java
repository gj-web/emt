package mk.ukim.finki.mk.bookandstay_application.service.application;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateCountryDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayCountryDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(Long id);

}
