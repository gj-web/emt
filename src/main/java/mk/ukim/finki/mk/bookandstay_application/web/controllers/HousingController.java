package mk.ukim.finki.mk.bookandstay_application.web.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateHousingDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHousingDto;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import mk.ukim.finki.mk.bookandstay_application.model.views.HousingByHostView;
import mk.ukim.finki.mk.bookandstay_application.repository.HousingsByHostViewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.HousingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housings")
@Tag(name = "Hosuings", description = "Housings API")
public class HousingController {
    private final HousingApplicationService housingApplicationService;
    private final HousingsByHostViewRepository housingsByHostRepository;

    public HousingController(HousingApplicationService housingApplicationService, HousingsByHostViewRepository housingsByHostRepository) {
        this.housingApplicationService = housingApplicationService;
        this.housingsByHostRepository = housingsByHostRepository;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find housing", description = "Finds the housing by its ID")
    public ResponseEntity<DisplayHousingDto> findById(@PathVariable Long id) {
        return this.housingApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a housing")
    public ResponseEntity<DisplayHousingDto> save(@RequestBody CreateHousingDto housing) {
        return this.housingApplicationService.save(housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates a housing")
    public ResponseEntity<DisplayHousingDto> update(@PathVariable Long id, @RequestBody CreateHousingDto housing) {
        return this.housingApplicationService.update(id, housing)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a housing by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.housingApplicationService.findById(id).isPresent()) {
            this.housingApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Marks the housing as rented")
    public ResponseEntity<DisplayHousingDto> markAsRented(@PathVariable Long id) {
        return this.housingApplicationService.markedAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping
    @Operation(summary = "Lists all of the housings")
    public List<DisplayHousingDto> findAll(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) Category category,
                                           @RequestParam(required = false) Long hostId,
                                           @RequestParam(required = false) Integer numRooms) {
        return housingApplicationService.searchWithFilters(name, category, hostId, numRooms);
    }

    @GetMapping("/by-host")
    public List<HousingByHostView> getAccommodationsByHost() {
        return housingsByHostRepository.findAll();
    }

}
