package mk.ukim.finki.mk.bookandstay_application.service.domain.Impl;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;
import mk.ukim.finki.mk.bookandstay_application.repository.ReviewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.domain.HousingService;
import mk.ukim.finki.mk.bookandstay_application.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final HousingService housingService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, HousingService housingService) {
        this.reviewRepository = reviewRepository;
        this.housingService = housingService;
    }


    @Override
    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public Optional<Review> save(Review review) {
        return Optional.of(this.reviewRepository.save(new Review(review.getComment(), review.getGrade(), this.housingService.findById(review.getHousing().getId()).orElseThrow(() -> new RuntimeException("Host not found")))));
    }


    @Override
    public Optional<Review> findById(Long id) {
        return this.reviewRepository.findById(id);
    };
}
