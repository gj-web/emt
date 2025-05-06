package mk.ukim.finki.mk.bookandstay_application.service.application.Impl;

import mk.ukim.finki.mk.bookandstay_application.dto.CreateReviewDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayReviewDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;
import mk.ukim.finki.mk.bookandstay_application.repository.ReviewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.HousingApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.application.ReviewApplicationService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {

    private final ReviewService reviewService;
    private final HousingService housingService;

    public ReviewApplicationServiceImpl(ReviewService reviewService, HousingService housingService) {
        this.reviewService = reviewService;
        this.housingService = housingService;
    }


    @Override
    public List<DisplayReviewDto> findAll() {

        return DisplayReviewDto.from(reviewService.findAll());
    }

    @Override
    public Optional<DisplayReviewDto> save(CreateReviewDto review) {
        Optional<Housing> housing = housingService.findById(review.housing());
        if (housing.isPresent()) {
            return reviewService.save(review.toReview(housing.get()))
                    .map(DisplayReviewDto::from);
        }
        return Optional.empty();

    }

    @Override
    public Optional<DisplayReviewDto> findById(Long id) {
        return this.reviewService.findById(id).map(DisplayReviewDto::from);
    };
}
