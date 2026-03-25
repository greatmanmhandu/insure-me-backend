package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Bank;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPayment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {

}

