package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.BankedAmountsForAlteration;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.FuneralOption;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPayment;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AlteredPayments;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequestDash;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PaymentService {

    ResponseEntity<BaseResult> getAllPayments();

    ResponseEntity<BaseResult> getAllPaymentsByUsername(String username);

    ResponseEntity<BaseResult> savePayment(PaymentRequest paymentRequest);

    ResponseEntity<BaseResult> getAllPaymentsByAgentId(Long agentId);

    ResponseEntity<BaseResult> getAllPaymentsByAgentNumber(String agentNumber);

    ResponseEntity<BaseResult> getGrandTotalsByAgentNumber(String agentNumber);

    ResponseEntity<BaseResult> getGrandTotalsByAgentNumberAndSBU(String agentNumber, String sbu);

    ResponseEntity<BaseResult> getPremiumsByFilter(String startDate, String endDate, String agentNumber, String username) throws ParseException;

    void updatePremiumStatusForClosedShift(List<Long> primiumIds);

    void updatePremiumStatusAfterCashUp(List<PremiumPayment> premiumPayment, String cashUpStatus);



    ResponseEntity<BaseResult> editsavePayment(PaymentRequestDash paymentRequest);

    ResponseEntity<BaseResult> alterPremium(PremiumPaymentForAlteration premiumPaymentForAlteration);

    ResponseEntity<BaseResult> getAllAlteredPremiumPaymentsByAgent(String agentNumber);

    ResponseEntity<BaseResult> getAllAlteredBankedDetailsPaymentsByAgent(String agentNumber);

    ResponseEntity<BaseResult> alterBankingDetails(BankedAmountsForAlteration bankedAmountsForAlteration);

    ResponseEntity<BaseResult> getAllAlteredPremiumPayments();

    ResponseEntity<BaseResult> getPaymentById(Long premiumId);

    ResponseEntity<BaseResult> updateAlterarionByFinance(AlteredPayments alteredPayments);

    ResponseEntity<BaseResult> getAllAlteredBankingDetails();

    ResponseEntity<BaseResult> checkPendingAlteration(String agentNumber);

    ResponseEntity<BaseResult> getPremiumsRejectedByFilter(String startDate, String endDate, String agentNumber, String username) throws ParseException;

    ResponseEntity<BaseResult> getPremiumsAccountByFilter(String startDate, String endDate, String policyNumber, String username) throws ParseException;

    ResponseEntity<BaseResult> getAllFuneralOptions();

    ResponseEntity<BaseResult> saveFuneralOptions(FuneralOption funeralOption);

    ResponseEntity<BaseResult> getAllRejectedPaymentsByAgentNumber(String agentNumber);

    ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumber(String agentNumber);

    ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumberAndSBU(String agentNumber, String sbu);

    BaseResult findConsistentPolicyHoldersInLastFourMonths();

    BaseResult getConsistentPolicyHoldersLastSixMonths(String place);

    String updatePolicyNumbersFromFile(MultipartFile file) throws IOException;

    ResponseEntity<BaseResult> getPaymentsByPolicy(String policy);
}

