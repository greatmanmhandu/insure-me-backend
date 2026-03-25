package zw.co.lifewebcore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.lifewebcore.domain.model.NewBusinessFuneralForEfmlandELP;


import java.time.Instant;
import java.util.List;

@Repository
public interface NewBusinessFuneralForEfmlandELPRepository extends JpaRepository<NewBusinessFuneralForEfmlandELP,Long> {


    List<NewBusinessFuneralForEfmlandELP> findAllByOrderByIdAsc();

    List<NewBusinessFuneralForEfmlandELP> findByCreatedDateBetweenOrderByIdAsc(Instant startDate, Instant endDate);
}