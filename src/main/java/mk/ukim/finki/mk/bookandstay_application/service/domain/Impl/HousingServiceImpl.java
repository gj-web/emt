package mk.ukim.finki.mk.bookandstay_application.service.domain.Impl;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import mk.ukim.finki.mk.bookandstay_application.repository.HousingRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.HousingsByHostViewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HostService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.mk.bookandstay_application.repository.HousingSpecification.*;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRepository housingRepository;
    private final HostService hostService;

    private final HousingsByHostViewRepository housingsByHostViewRepository;

    public HousingServiceImpl(HousingRepository housingRepository, HostService hostService, HousingsByHostViewRepository housingsByHostViewRepository) {
        this.housingRepository = housingRepository;
        this.hostService = hostService;
        this.housingsByHostViewRepository = housingsByHostViewRepository;
    }

    @Override
    public List<Housing> findAll() {
        return this.housingRepository.findAll();
    }

    @Override
    public Optional<Housing> save(Housing housing) {
        return Optional.of(this.housingRepository.save(new Housing(housing.getName(), housing.getCategory(), this.hostService.findById(housing.getHost().getId()).orElseThrow(() -> new RuntimeException("Host not found")), housing.getNumRooms())));
    }

    @Override
    public Optional<Housing> findById(Long id) {
        return this.housingRepository.findById(id);
    }

    @Override
    public Optional<Housing> update(Long id, Housing housing) {
        return this.housingRepository.findById(id)
                .map(existingAccommodation -> {
                    if (housing.getName() != null) {
                        existingAccommodation.setName(housing.getName());
                    }
                    if (housing.getCategory() != null) {
                        existingAccommodation.setCategory(housing.getCategory());
                    }
                    if (housing.getHost() != null && this.hostService.findById(housing.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(this.hostService.findById(housing.getHost().getId()).get());
                    }
                    if (housing.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(housing.getNumRooms());
                    }
                    return this.housingRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Housing> markedAsRented(Long id) {
        return housingRepository.findById(id).map(housing -> {
            housing.setNumRooms(null);
            return housingRepository.save(housing);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.housingRepository.deleteById(id);
    }



    @Override
    public List<Housing> searchWithFilters(String name, Category category, Long hostId, Integer numRooms) {
        return housingRepository.findAll(
                Specification.where(hasName(name))
                        .and(hasCategory(category))
                        .and(hasHostId(hostId))
                        .and(hasNumRooms(numRooms))
        );
    }

    @Override
    public void refreshMaterializedView() {
        this.housingsByHostViewRepository.refreshMaterializedView();
    }


}
