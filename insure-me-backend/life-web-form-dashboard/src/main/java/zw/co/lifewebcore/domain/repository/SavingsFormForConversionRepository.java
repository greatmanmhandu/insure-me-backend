package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;
import zw.co.lifewebcore.domain.model.SavingsFormForConversion;

import java.time.Instant;
import java.util.List;

@Repository
public interface SavingsFormForConversionRepository extends JpaRepository<SavingsFormForConversion,Long> {


    List<SavingsFormForConversion> findAllByOrderByIdAsc();

    List<SavingsFormForConversion> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}