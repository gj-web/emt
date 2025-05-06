package mk.ukim.finki.mk.bookandstay_application.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.housings_by_host")
@Immutable
public class HousingByHostView {

    @Id
    @Column(name = "id_housing")
    private Long id;

    @Column(name = "num_housings")
    private Integer numHousings;
}
