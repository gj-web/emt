package mk.ukim.finki.mk.bookandstay_application.service.application.Impl;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateCountryDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayCountryDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.repository.CountryRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.CountryApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {
        return countryService.update(id, country.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
