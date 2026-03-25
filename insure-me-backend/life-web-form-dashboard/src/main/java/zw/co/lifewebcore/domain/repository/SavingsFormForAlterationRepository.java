package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;
import zw.co.lifewebcore.domain.model.SavingsFormForAlteration;

import java.time.Instant;
import java.util.List;

@Repository
public interface SavingsFormForAlterationRepository extends JpaRepository<SavingsFormForAlteration,Long> {

    List<SavingsFormForAlteration> findAllByOrderByIdAsc();

    List<SavingsFormForAlteration> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}