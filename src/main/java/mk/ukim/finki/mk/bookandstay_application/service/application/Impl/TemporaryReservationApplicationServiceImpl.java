package mk.ukim.finki.mk.bookandstay_application.service.application.Impl;

import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHousingDto;
import mk.ukim.finki.mk.bookandstay_application.dto.TemporaryReservationDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.TemporaryReservation;
import mk.ukim.finki.mk.bookandstay_application.model.domain.User;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.HousingNotAvailable;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.HousingNotFound;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.NoTemporaryReservations;
import mk.ukim.finki.mk.bookandstay_application.repository.TemporaryReservationRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final HousingService housingService;
    private final UserService userService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, HousingService housingService, UserService userService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.housingService = housingService;
        this.userService = userService;
    }

    @Override
    public List<DisplayHousingDto> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return DisplayHousingDto.from(reservation.getHousings());
    }

    @Override
    public Optional<TemporaryReservationDto> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Housing housing = housingService.findById(accommodationId).orElseThrow(HousingNotFound::new);
        if (housing.isRented()) {
            throw new HousingNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(() -> {
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setHousings(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getHousings().contains(housing)) {
            reservation.getHousings().add(housing);
        }

        TemporaryReservation saved = temporaryReservationRepository.save(reservation);
        return Optional.of(TemporaryReservationDto.from(saved));
    }

    @Override
    public Optional<TemporaryReservationDto> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Housing housing : reservation.getHousings()) {
            housing.setRented(true);
            housingService.save(housing);
        }
        TemporaryReservationDto dto = TemporaryReservationDto.from(reservation);
        temporaryReservationRepository.delete(reservation);
        return Optional.of(dto);
    }

    @Override
    public List<DisplayHousingDto> listAllHousingsInTemporaryReservation(String username) {
        return List.of();
    }
}