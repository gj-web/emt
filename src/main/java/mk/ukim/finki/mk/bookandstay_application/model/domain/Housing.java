package mk.ukim.finki.mk.bookandstay_application.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;

@Data
@Entity
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;

    boolean isRented = false;

    public Housing() {
    }

    public Housing(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.numRooms = numRooms;
        this.host = host;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public Host getHost() {
        return host;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
