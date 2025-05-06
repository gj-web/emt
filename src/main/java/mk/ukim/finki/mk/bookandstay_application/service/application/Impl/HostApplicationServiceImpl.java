package mk.ukim.finki.mk.bookandstay_application.service.application.Impl;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateHostDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayCountryDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHostDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.repository.HostRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.CountryApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.application.HostApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.CountryService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public List<DisplayHostDto> findAll() {

        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.country());

        if (country.isPresent()) {
            return hostService.save(host.toHost(country.get()))
                    .map(DisplayHostDto::from);
        }
        return Optional.empty();
    }


    @Override
    public Optional<DisplayHostDto> findById(Long id) {

        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.country());
        return hostService.update(id,
                        host.toHost(
                                country.orElse(null)
                        )
                )
                .map(DisplayHostDto::from);
    }


    @Override
    public void deleteById(Long id) {

        this.deleteById(id);
    }
}
