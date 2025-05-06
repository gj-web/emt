package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

public record CreateCountryDto (
        String name,
        String continent
){
    public Country toCountry() {
        return new Country(name,continent);
    }
}
