package mk.ukim.finki.mk.bookandstay_application.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    public Host(String name, Country country, String surname) {
        this.name = name;
        this.country = country;
        this.surname = surname;
    }

    public Host() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
