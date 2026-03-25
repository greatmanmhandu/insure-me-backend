package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;
import zw.co.lifewebcore.domain.model.NewBusinessFuneralForEfmlandELP;

import java.time.Instant;
import java.util.List;

@Repository
public interface AlterationFormForfuneralRepository extends JpaRepository<AlterationFormForfuneral,Long> {


    List<AlterationFormForfuneral> findAllByOrderByIdAsc();

    List<AlterationFormForfuneral> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}