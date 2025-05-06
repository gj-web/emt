package mk.ukim.finki.mk.bookandstay_application.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateCountryDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayCountryDto;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "Countries API")
public class CountryController {

 private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }


    @GetMapping
    @Operation(summary = "List all of the countries")
    public List<DisplayCountryDto> findAll() {
        return this.countryApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a country by an ID")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Save a country")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto country) {
        return this.countryApplicationService.save(country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update a country")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto country) {
        return countryApplicationService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country by an ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.countryApplicationService.findById(id).isPresent()) {
            this.countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
