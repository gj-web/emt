package mk.ukim.finki.mk.bookandstay_application.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
