package mk.ukim.finki.mk.bookandstay_application.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class TemporaryReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany
    private List<Housing> housings;

    public TemporaryReservation(){

    }

    public TemporaryReservation(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Housing> getHousings() {
        return housings;
    }

    public void setHousings(List<Housing> housings) {
        this.housings= housings;
    }
}