package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SubAreaOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubAreaOfficeRepository extends JpaRepository<SubAreaOffice, Long> {

    List<SubAreaOffice> findAllByAreaOfficeId(Long areaOfficeId);
}

