package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SWUpdates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SWRepository extends JpaRepository<SWUpdates, Long> {

}

