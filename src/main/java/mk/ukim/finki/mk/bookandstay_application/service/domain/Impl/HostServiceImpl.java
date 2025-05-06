package mk.ukim.finki.mk.bookandstay_application.service.domain.Impl;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.repository.HostRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.domain.CountryService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.of(this.hostRepository.save(new Host(host.getName(),this.countryService.findById(host.getCountry().getId()).orElse(null), host.getSurname())));
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id)
                .map(existingHost -> {
                    if (host.getName() != null) {
                        existingHost.setName(host.getName());
                    }
                    if (host.getSurname() != null) {
                        existingHost.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null) {
                        existingHost.setCountry(this.countryService.findById(host.getCountry().getId()).orElse(null));
                    }
                    return hostRepository.save(existingHost);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        this.hostsPerCountryViewRepository.refreshMaterializedView();
    }
}
