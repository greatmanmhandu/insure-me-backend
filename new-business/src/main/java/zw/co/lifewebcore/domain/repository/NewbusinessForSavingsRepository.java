package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.NewBusinessFuneralForEfmlandELP;
import zw.co.lifewebcore.domain.model.NewbusinessForSavings;

import java.time.Instant;
import java.util.List;

@Repository
public interface NewbusinessForSavingsRepository extends JpaRepository<NewbusinessForSavings,Long> {


    List<NewbusinessForSavings> findAllByOrderByIdAsc();

    List<NewbusinessForSavings> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}