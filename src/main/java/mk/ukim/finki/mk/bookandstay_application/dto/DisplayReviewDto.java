package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayReviewDto(
        String comment,
        Long grade,
        Long housing
) {

    public static DisplayReviewDto from(Review review) {
        return new DisplayReviewDto(
                review.getComment(),
                review.getGrade(),
                review.getHousing().getId()
        );
    }

    public static List<DisplayReviewDto> from(List<Review> reviews) {
        return reviews.stream()
                .map(DisplayReviewDto::from)
                .collect(Collectors.toList());
    }

    public Review toReview(Housing housing) {
        return new Review(comment, grade, housing);
    }
}
