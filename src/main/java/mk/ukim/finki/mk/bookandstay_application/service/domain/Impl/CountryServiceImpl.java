package mk.ukim.finki.mk.bookandstay_application.service.domain.Impl;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.repository.CountryRepository;
import mk.ukim.finki.mk.bookandstay_application.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(this.countryRepository.save(new Country(country.getName(),country.getContinent())));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return this.countryRepository.findById(id).map(existingCategory -> {
            if (country.getName() != null) {
                existingCategory.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCategory.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCategory);
        });

    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
