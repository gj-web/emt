package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;
import java.util.List;
import java.util.stream.Collectors;

public record CreateReviewDto (
        String comment,
        Long grade,
        Long housing
){
    public static CreateReviewDto from(Review review){
        return new CreateReviewDto(
                review.getComment(),
                review.getGrade(),
                review.getHousing().getId()
        );
    }
    public static List<CreateReviewDto> from(List<Review> products) {
        return products.stream().map(CreateReviewDto::from).collect(Collectors.toList());
    }

    public Review toReview(Housing housing) {
        return new Review(comment, grade, housing);
    }

}
