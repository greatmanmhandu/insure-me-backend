package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPayment;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShiftLoggingRepository extends JpaRepository<ShiftLogin, Long> {

    List<ShiftLogin> findAllByUsername(String username);

    List<ShiftLogin> findAllByAgentId(Long agentId);

    Optional<ShiftLogin> findByAgentNumber(String agentNumber);

    Optional<ShiftLogin> findByAgentNumberOrderByIdDesc(String agentNumber);

    Optional<ShiftLogin> findByAgentNumberAndStartDate(String agentNumber, String currentDate);

    List<ShiftLogin> findAllByTenderUserName(String tenderUserName);

    List<ShiftLogin> findAllByTenderUserNameAndEndDateTimeNotNull(String tenderUserName);

    List<ShiftLogin> findAllByEndDateTimeBetween(Date startDate, Date endDate);

    List<ShiftLogin> findAllByAgentNumberInAndEndDateTimeBetween(List<String> agentNumbers, Date startDate, Date endDate);

    List<ShiftLogin> findAllByFinanceStatus(String pending);

    List<ShiftLogin> findAllByFinanceStatusAndCashUpStatusAndConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(String pending, String approved, String consolidated, BigDecimal zero);

    List<ShiftLogin> findAllByCashUpStatusAndConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(String approved, String consolidated, BigDecimal zero);

    List<ShiftLogin> findAllByConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(String consolidated, BigDecimal zero);


    List<ShiftLogin> findAllByCashUpStatusNotAndConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(String approved, String consolidated, BigDecimal zero);

    List<ShiftLogin> findAllByCashUpStatusNotAndTotalPaymentCashUSDGreaterThan(String approved, BigDecimal zero);

    List<ShiftLogin> findAllByOrderByIdDesc();
}

