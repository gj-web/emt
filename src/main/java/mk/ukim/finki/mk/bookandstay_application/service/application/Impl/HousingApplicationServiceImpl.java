package mk.ukim.finki.mk.bookandstay_application.service.application.Impl;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateHousingDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHousingDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import mk.ukim.finki.mk.bookandstay_application.repository.HousingRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.HostApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.application.HousingApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HostService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingApplicationServiceImpl implements HousingApplicationService {

    private final HousingService housingService;
    private final HostService hostService;

    public HousingApplicationServiceImpl(HousingService housingService, HostService hostService) {
        this.housingService = housingService;
        this.hostService = hostService;
    }


    @Override
    public List<DisplayHousingDto> findAll() {

        return DisplayHousingDto.from(housingService.findAll());
    }

    @Override
    public Optional<DisplayHousingDto> save(CreateHousingDto housing) {
        Optional<Host> host = hostService.findById(housing.host());
        if (host.isPresent() ) {
            return housingService.save(housing.toHousing(host.get()))
                    .map(DisplayHousingDto::from);
        }
        return Optional.empty();

    }

    @Override
    public Optional<DisplayHousingDto> findById(Long id) {
        return housingService.findById(id).map(DisplayHousingDto::from);
    }

    @Override
    public Optional<DisplayHousingDto> update(Long id, CreateHousingDto housing) {
        Optional<Host> host = hostService.findById(housing.host());
        return housingService.update(id,
                        housing.toHousing(
                                host.orElse(null)
                        )
                )
                .map(DisplayHousingDto::from);
    }

    @Override
    public Optional<DisplayHousingDto> markedAsRented(Long id) {
        return housingService.findById(id).map(housing -> {
            housing.setNumRooms(null);
            return housingService.save(housing).map(DisplayHousingDto::from).orElse(null);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.housingService.deleteById(id);
    }

    @Override
    public List<DisplayHousingDto> searchWithFilters(String name, Category category, Long hostId, Integer numRooms) {
        return housingService.searchWithFilters(name, category, hostId, numRooms)
                .stream()
                .map(DisplayHousingDto::from)
                .toList();
    }



//    @Override
//    public List<Housing> searchWithFilters(String name, Category category, Long hostId) {
//        if (name != null && category != null && hostId != null) {
//            return housingService.findAllByNameContainingIgnoreCaseAndCategoryAndHost_Id(name, category, hostId).map(DisplayHousingDto::from);
//        } else if (name != null && category != null) {
//            return housingService.findAllByNameContainingIgnoreCaseAndCategory(name, category);
//        } else if (name != null && hostId != null) {
//            return housingService.findAllByNameContainingIgnoreCaseAndHost_Id(name, hostId);
//        } else if (category != null && hostId != null) {
//            return housingService.findAllByCategoryAndHost_Id(category, hostId);
//        } else if (name != null) {
//            return housingService.findAllByNameContainingIgnoreCase(name);
//        } else if (category != null) {
//            return housingService.findAllByCategory(category);
//        } else if (hostId != null) {
//            return housingService.findAllByHost_Id(hostId);
//        } else {
//            return housingService.findAll();
//        }
//    }


}
