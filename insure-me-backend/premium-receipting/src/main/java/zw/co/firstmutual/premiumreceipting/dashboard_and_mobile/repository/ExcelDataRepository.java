package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ExcelMemberData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelMemberData, Long> {
}

