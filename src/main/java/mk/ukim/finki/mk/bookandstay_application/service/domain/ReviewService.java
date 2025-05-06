package mk.ukim.finki.mk.bookandstay_application.service.domain;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> findAll();

    Optional<Review> save(Review review);

    Optional<Review> findById(Long id);

}
