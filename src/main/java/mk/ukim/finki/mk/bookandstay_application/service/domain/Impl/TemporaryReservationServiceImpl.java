package mk.ukim.finki.mk.bookandstay_application.service.domain.Impl;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.TemporaryReservation;
import mk.ukim.finki.mk.bookandstay_application.model.domain.User;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.HousingNotAvailable;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.HousingNotFound;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.NoTemporaryReservations;
import mk.ukim.finki.mk.bookandstay_application.repository.TemporaryReservationRepository;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.TemporaryReservationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationServiceImpl implements TemporaryReservationService {
    private final TemporaryReservationRepository temporaryReservationRepository;
    private final UserService userService;
    private final HousingService housingService;

    public TemporaryReservationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, UserService userService, HousingService housingService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.userService = userService;
        this.housingService = housingService;
    }

    @Override
    public List<Housing> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return reservation.getHousings();
    }

    @Override
    public Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Housing housing = housingService.findById(accommodationId).orElseThrow(HousingNotFound::new);
        if (housing.isRented()){
            throw new HousingNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(()->{
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setHousings(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getHousings().contains(housing)){
            reservation.getHousings().add(housing);
        }
        return Optional.of(temporaryReservationRepository.save(reservation));
    }

    @Override
    public Optional<TemporaryReservation> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Housing housing : reservation.getHousings()){
            housing.setRented(true);
            housingService.save(housing);
        }
        temporaryReservationRepository.delete(reservation);
        return Optional.of(reservation);
    }
}