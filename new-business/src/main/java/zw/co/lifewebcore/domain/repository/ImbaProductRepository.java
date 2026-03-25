package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;
import zw.co.lifewebcore.domain.model.ImbaProduct;

import java.time.Instant;
import java.util.List;

@Repository
public interface ImbaProductRepository extends JpaRepository<ImbaProduct,Long> {

    List<ImbaProduct> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}