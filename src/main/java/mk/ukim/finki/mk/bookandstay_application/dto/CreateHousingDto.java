package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHousingDto (
        String name,
        Category category,
        Long host,
        Integer numRooms
){
    public static CreateHousingDto from(Housing housing){
        return new CreateHousingDto(
        housing.getName(),
        housing.getCategory(),
        housing.getHost().getId(),
        housing.getNumRooms()
        );
    }
    public static List<CreateHousingDto> from(List<Housing> housings) {
        return housings.stream().map(CreateHousingDto::from).collect(Collectors.toList());
    }

    public Housing toHousing(Host host) {
        return new Housing(name, category, host, numRooms);
    }
}
