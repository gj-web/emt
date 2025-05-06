package mk.ukim.finki.mk.bookandstay_application.service.application;

import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHostDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHousingDto;
import mk.ukim.finki.mk.bookandstay_application.dto.TemporaryReservationDto;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {
    List<DisplayHousingDto> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservationDto> confirmReservation(String username);

    List<DisplayHousingDto> listAllHousingsInTemporaryReservation(String username);
}