package mk.ukim.finki.mk.bookandstay_application.repository;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import org.springframework.data.jpa.domain.Specification;

public class HousingSpecification{

    public static Specification<Housing> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Housing> hasCategory(Category category) {
        return (root, query, criteriaBuilder) ->
                category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Housing> hasHostId(Long hostId) {
        return (root, query, criteriaBuilder) ->
                hostId == null ? null : criteriaBuilder.equal(root.get("host").get("id"), hostId);
    }

    public static Specification<Housing> hasNumRooms(Integer numRooms) {
        return (root, query, cb) ->
                numRooms == null ? null : cb.equal(root.get("numRooms"), numRooms);
    }
}
