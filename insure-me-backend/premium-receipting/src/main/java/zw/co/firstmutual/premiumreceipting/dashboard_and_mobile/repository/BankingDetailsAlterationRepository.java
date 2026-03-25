package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.BankedAmountsForAlteration;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankingDetailsAlterationRepository extends JpaRepository<BankedAmountsForAlteration, Long> {

    List<BankedAmountsForAlteration>  findAllByAgentNumberAndType(String agentNumber, String type);
}

