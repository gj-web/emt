package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHousingDto(
        Long id,
        String name,
        Category category,
        Long host,
        Integer numRooms
) {
    public static DisplayHousingDto from(Housing housing){
        return new DisplayHousingDto(
                housing.getId(),
                housing.getName(),
                housing.getCategory(),
                housing.getHost().getId(),
                housing.getNumRooms()
        );}

    public static List<DisplayHousingDto> from(List<Housing> housings){
        return housings.stream().map(DisplayHousingDto::from).collect(Collectors.toList());
    }

    public Housing toHousing(Host host){
        return new Housing(name,category,host,numRooms);
    }
}
