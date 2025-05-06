package mk.ukim.finki.mk.bookandstay_application.service.domain;



import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {
    List<Housing> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservation> confirmReservation(String username);

}