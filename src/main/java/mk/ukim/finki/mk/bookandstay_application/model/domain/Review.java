package mk.ukim.finki.mk.bookandstay_application.model.domain;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Long grade;

    @ManyToOne
    private Housing housing;

    public Review(String comment, long grade, Housing housing) {
        this.comment = comment;
        this.grade = grade;
        this.housing = housing;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Long getGrade() {
        return grade;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }
}
