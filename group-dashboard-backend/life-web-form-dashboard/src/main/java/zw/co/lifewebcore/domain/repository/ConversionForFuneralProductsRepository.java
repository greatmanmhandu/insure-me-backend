package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;
import zw.co.lifewebcore.domain.model.ConversionForFuneralProducts;

import java.time.Instant;
import java.util.List;

@Repository
public interface ConversionForFuneralProductsRepository extends JpaRepository<ConversionForFuneralProducts,Long> {


    List<ConversionForFuneralProducts> findAllByOrderByIdAsc();

    List<ConversionForFuneralProducts> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}