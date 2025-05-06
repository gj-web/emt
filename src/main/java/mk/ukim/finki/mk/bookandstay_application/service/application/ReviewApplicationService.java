package mk.ukim.finki.mk.bookandstay_application.service.application;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateReviewDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayReviewDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {
    List<DisplayReviewDto> findAll();

    Optional<DisplayReviewDto> save(CreateReviewDto review);

    Optional<DisplayReviewDto> findById(Long id);

}
