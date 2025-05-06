package mk.ukim.finki.mk.bookandstay_application.repository;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HousingRepository extends JpaRepository<Housing, Long>, JpaSpecificationExecutor<Housing> {
    List<Housing> findAllByNameContainingIgnoreCaseAndCategoryAndHost_Id(String name, Category category, Long hostId);

    List<Housing> findAllByNameContainingIgnoreCaseAndCategory(String name, Category category);

    List<Housing> findAllByNameContainingIgnoreCaseAndHost_Id(String name, Long hostId);

    List<Housing> findAllByCategoryAndHost_Id(Category category, Long hostId);

    List<Housing> findAllByNameContainingIgnoreCase(String name);

    List<Housing> findAllByCategory(Category category);

    List<Housing> findAllByHost_Id(Long hostId);
}
