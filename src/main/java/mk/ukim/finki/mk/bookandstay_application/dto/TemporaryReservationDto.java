package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.TemporaryReservation;

import java.util.List;

public record TemporaryReservationDto(
        Long id,
        DisplayUserDto user,
        List<DisplayHousingDto> housings
) {
    public static TemporaryReservationDto from(TemporaryReservation temporaryReservation){
        return new TemporaryReservationDto(
                temporaryReservation.getId(),
                DisplayUserDto.from(temporaryReservation.getUser()),
                DisplayHousingDto.from(temporaryReservation.getHousings())
        );
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public DisplayUserDto user() {
        return user;
    }

    @Override
    public List<DisplayHousingDto> housings() {
        return housings;
    }
}