package mk.ukim.finki.mk.bookandstay_application.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateReviewDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayReviewDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Review;
import mk.ukim.finki.mk.bookandstay_application.service.application.ReviewApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review API", description = "Endpoints for the reviews of the users")
public class ReviewController {
    private final ReviewApplicationService reviewApplicationService;

    public ReviewController(ReviewApplicationService reviewApplicationService) {
        this.reviewApplicationService = reviewApplicationService;
    }


    @GetMapping
    @Operation(summary = "Find the reviews", description = "Lists all of the reviews")
    public List<DisplayReviewDto> findAll() {
        return this.reviewApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayReviewDto> findById(@PathVariable Long id) {
        return reviewApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayReviewDto> save(@RequestBody CreateReviewDto review) {
        return this.reviewApplicationService.save(review)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
