package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.MombeProduct;

import java.time.Instant;
import java.util.List;

@Repository
public interface MombeProductRepository extends JpaRepository<MombeProduct, Long> {
    List<MombeProduct> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}
