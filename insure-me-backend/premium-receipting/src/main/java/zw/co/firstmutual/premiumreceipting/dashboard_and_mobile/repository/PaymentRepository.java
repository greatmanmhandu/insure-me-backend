package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPayment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PremiumPayment, Long> {
    List<PremiumPayment> findByAgentId(Long userId);

    List<PremiumPayment> findAllByUsername(String username);

    List<PremiumPayment> findAllByAgentId(Long agentId);

    List<PremiumPayment> findAllByAgentNumber(String agentNumber);


    List<PremiumPayment> findAllByAgentNumberInAndPaymentDateBetween(List<String> agentIds, Date startDate, Date endDate);

    List<PremiumPayment> findAllByPaymentDateBetween(Date startDate, Date endDate);

    List<PremiumPayment> findAllByAgentNumberAndCommitStatus(String agentNumber, String commitStatus);

    List<PremiumPayment> findAllByIdIn(List<Long> primiumIds);

    List<PremiumPayment> findAllByAgentNumberAndAlterationStatus(String agentNumber, String alterationStatus);

    List<PremiumPayment> findAllByAgentNumberInAndPaymentDateBetweenAndCommitStatus(List<String> agentNumbers, Date startDate, Date endDate, String rejected);

    List<PremiumPayment> findAllByPaymentDateBetweenAndCommitStatus(Date startDate, Date endDate, String rejected);

    List<PremiumPayment> findAllByPaymentDateBetweenAndPolicyNumber(Date startDate, Date endDate, String policyNumber, Sort sort);

    List<PremiumPayment> findByPolicyNumberAndCreationDateBetweenOrderByCreationDateDesc(String policyNumber, Date startDate, Date endDate);

    // MODIFIED: Added AND pp.policyNumber != ''
//    @Query("SELECT DISTINCT pp.policyNumber FROM PremiumPayment pp " +
//            "WHERE pp.creationDate >= :fourMonthsAgo " +
//            "AND pp.policyNumber IS NOT NULL " +
//            "AND pp.policyNumber != ''") // <--- Added this condition
//    List<String> findDistinctPolicyNumbersWithPaymentsInLastFourMonths(
//            @Param("fourMonthsAgo") Date fourMonthsAgo);

    // MODIFIED: Added AND pp.policyNumber != ''
    @Query("SELECT pp FROM PremiumPayment pp " +
            "WHERE pp.policyNumber IN :policyNumbers " +
            "AND pp.creationDate >= :fourMonthsAgo " +
            "AND pp.policyNumber IS NOT NULL " +
            "AND pp.policyNumber != '' " + // <--- Added this condition
            "ORDER BY pp.policyNumber, pp.creationDate DESC")
    List<PremiumPayment> findAllPaymentsForPolicyNumbersInLastSixMonths(
            @Param("policyNumbers") List<String> policyNumbers,
            @Param("fourMonthsAgo") Date fourMonthsAgo);
    @Query("SELECT DISTINCT pp.policyNumber FROM PremiumPayment pp " +
            "WHERE pp.creationDate >= :sixMonthsAgo " +
            "AND pp.policyNumber IS NOT NULL " +
            "AND pp.policyNumber != ''") // <--- Added this condition
    List<String> findDistinctPolicyNumbersWithPaymentsInLastSixMonths(
            @Param("sixMonthsAgo") Date sixMonthsAgo);

    List<PremiumPayment> findAllByPolicyNumber(String policy);
}

