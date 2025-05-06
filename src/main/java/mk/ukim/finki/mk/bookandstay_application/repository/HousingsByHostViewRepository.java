package mk.ukim.finki.mk.bookandstay_application.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.mk.bookandstay_application.model.views.HousingByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingsByHostViewRepository extends JpaRepository<HousingByHostView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.housings_by_host", nativeQuery = true)
    void refreshMaterializedView();

}
