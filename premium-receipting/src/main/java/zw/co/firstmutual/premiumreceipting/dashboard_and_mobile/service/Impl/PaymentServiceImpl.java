package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AlteredPayments;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequestDash;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.GrantPaymentResponse;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.PaymentTransactionDTO;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.PolicyHolderPaymentDetailsDTO;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AgentService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.PaymentService;
import zw.co.firstmutual.premiumreceipting.enums.Channel;
import zw.co.firstmutual.premiumreceipting.enums.Currency;
import zw.co.firstmutual.premiumreceipting.enums.PaymentStatus;
import zw.co.firstmutual.premiumreceipting.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PremiumAlterationRepository premiumAlterationRepository;
    private final BankingDetailsAlterationRepository bankingDetailsAlterationRepository;
    private  final FuneralOptionRepository funeralOptionRepository;
    private final PolicyHolderRepository policyHolderRepository;
    private static final String ID_HEADER = "id";
    private static final String POLICY_NUMBER_HEADER = "policynumber"; // Stored in lowercase for comparison
    @Override
    public ResponseEntity<BaseResult> getAllPayments() {
        List<PremiumPayment> payments = paymentRepository.findAll();
        // Group payments by areaOfficeName and calculate the sum of payableAmount for each areaOfficeName
        Map<String, BigDecimal> areaOfficeSumMap = payments.stream()
                .collect(Collectors.groupingBy(PremiumPayment::getAreaOfficeName,
                        Collectors.mapping(PremiumPayment::getPayableAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

// Create a new list of PremiumPayment objects with the sum included
        List<PremiumPayment> paymentsWithSum = payments.stream()
                .map(payment -> {
                    BigDecimal sum = areaOfficeSumMap.get(payment.getAreaOfficeName());
                    payment.setTotalSum(sum);

                    return payment;
                })
                .collect(Collectors.toList());

// Return the updated list of PremiumPayment objects
        return ResponseEntity.ok(new BaseResult(paymentsWithSum, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllPaymentsByUsername(String username) {
        return ResponseEntity.ok(new BaseResult(paymentRepository.findAllByUsername(username), "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> savePayment(PaymentRequest paymentRequest) {
        PremiumPayment premiumPayment = new PremiumPayment();
        premiumPayment.setAgentNumber(paymentRequest.getAgentNumber());
        premiumPayment.setPayableAmount(paymentRequest.getPayableAmount());
        premiumPayment.setStatus(Status.ACTIVE);
        premiumPayment.setPolicyNumber(paymentRequest.getPolicyNumber());
        premiumPayment.setPaymentStatus(PaymentStatus.PAID);
        premiumPayment.setCurrency(paymentRequest.getCurrency());
        premiumPayment.setTransactionId(paymentRequest.getTransactionId());
        premiumPayment.setPayableMobileNumber(paymentRequest.getPayablePhoneNumber());
        premiumPayment.setPaymentOption(paymentRequest.getPaymentOption());
        premiumPayment.setSbu(paymentRequest.getSbu());
        premiumPayment.setPaymentDate(new Date());
        premiumPayment.setCreationDate(new Date());
        premiumPayment.setCreatedBy(paymentRequest.getAgentNumber());
        premiumPayment.setSubAreaOfficeName(paymentRequest.getSubAreaOfficeName());
        premiumPayment.setAreaOfficeName(paymentRequest.getAreaOfficeName());
        premiumPayment.setMobileNumber(paymentRequest.getMobileNumber());
        premiumPayment.setPolicyHolderFirstName(paymentRequest.getPolicyHolderFirstName());
        premiumPayment.setPolicyHolderLastName(paymentRequest.getPolicyHolderLastName());
        premiumPayment.setPolicyHolderContractPremium(paymentRequest.getPolicyHolderContractPremium());
        premiumPayment.setCommitStatus("NOT COMMITTED");
        premiumPayment.setPolicyHolderIDNumber(paymentRequest.getPolicyHolderIDNumber());
        return ResponseEntity.ok(new BaseResult(paymentRepository.save(premiumPayment), "payment saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllPaymentsByAgentId(Long agentId) {
        return ResponseEntity.ok(new BaseResult(paymentRepository.findAllByAgentId(agentId), "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllPaymentsByAgentNumber(String agentNumber) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);
        List<PremiumPayment> payments = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber,"NOT COMMITTED");
//        List<PremiumPayment> payments = paymentRepository.findAllByAgentNumber(agentNumber).stream()
//                .filter(p ->  isSameDate(p.getPaymentDate(), currentDate) ).collect(Collectors.toList());
        return ResponseEntity.ok(new BaseResult(payments, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getGrandTotalsByAgentNumber(String agentNumber) {
//        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumber(agentNumber);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);

        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber,"NOT COMMITTED");


        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") && p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashZIG = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH ZIG") )
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashZIG = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH ZIG"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalPaymentCashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") &&  !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        GrantPaymentResponse grantPaymentResponse = new GrantPaymentResponse();
        grantPaymentResponse.setAgentNumber(agentNumber);
        grantPaymentResponse.setTotalPaymentCashUSD(totalPaymentCashUSD);
        grantPaymentResponse.setTotalPaymentCashZIG(totalPaymentCashZIG);
        grantPaymentResponse.setTotalPaymentEcocashUSD(totalPaymentEcocashUSD);
        grantPaymentResponse.setTotalPaymentEcocashZIG(totalPaymentEcocashZIG);
        grantPaymentResponse.setTotalPaymentCashUSDFuneral(totalPaymentCashUSDFuneral);
        grantPaymentResponse.setTotalPaymentEcocashUSDFuneral(totalPaymentEcocashUSDFuneral);
        grantPaymentResponse.setPaymentDate(currentDate.substring(0,10));
        List<Long> premiumPaymentIds = premiumPayment.stream()
                .map(PremiumPayment::getId) // Assuming getId method exists in PremiumPayment
                .collect(Collectors.toList());
        grantPaymentResponse.setPrimiumIds(premiumPaymentIds);

        return ResponseEntity.ok(new BaseResult(grantPaymentResponse, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getGrandTotalsByAgentNumberAndSBU(String agentNumber, String sbu) {
//        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumber(agentNumber);
        System.out.println(sbu);
        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber, "NOT COMMITTED");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);

        System.out.println("currentDate............");
        System.out.println(currentDate);

        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("CASH USD")  && p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("ECOCASH USD")  &&  p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber) &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && p.getSbu().equals(sbu) && p.getPaymentDate().toLocalDate().equals(currentDate.toLocalDate()))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashZIG = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("CASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber) &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") &&   p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


//        BigDecimal totalPaymentEcocashZIG = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

        GrantPaymentResponse grantPaymentResponse = new GrantPaymentResponse();
        grantPaymentResponse.setAgentNumber(agentNumber);
        grantPaymentResponse.setTotalPaymentCashUSD(sbu.equals("FIRST MUTUAL FUNERAL SERVICES")? BigDecimal.valueOf(0) :totalPaymentCashUSD);
        grantPaymentResponse.setTotalPaymentCashZIG(totalPaymentCashZIG);
        grantPaymentResponse.setTotalPaymentEcocashUSD(sbu.equals("FIRST MUTUAL FUNERAL SERVICES")? BigDecimal.valueOf(0) :totalPaymentEcocashUSD);
//        grantPaymentResponse.setTotalPaymentEcocashZIG(totalPaymentEcocashZIG);
        grantPaymentResponse.setPaymentDate(currentDate.substring(0,10));
        grantPaymentResponse.setTotalPaymentEcocashUSDFuneral(sbu.equals("FIRST MUTUAL FUNERAL SERVICES")? totalPaymentEcocashUSDFuneral :  BigDecimal.valueOf(0));
        grantPaymentResponse.setTotalPaymentCashUSDFuneral(sbu.equals("FIRST MUTUAL FUNERAL SERVICES")? totalPaymentCashUSDFuneral :  BigDecimal.valueOf(0) );

        List<Long> premiumPaymentIds = premiumPayment.stream()
                .map(PremiumPayment::getId) // Assuming getId method exists in PremiumPayment
                .collect(Collectors.toList());
        grantPaymentResponse.setPrimiumIds(premiumPaymentIds);
        return ResponseEntity.ok(new BaseResult(grantPaymentResponse, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getPremiumsByFilter(String startDateStr, String endDateStr, String agentNumber, String username) throws ParseException {
        System.out.println("in service");
        List<PremiumPayment> payments ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        // Set the time part of the dates to midnight
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        startDate = cal.getTime();

        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        endDate = cal.getTime();
        if(!agentNumber.equals("all")) {
            List<String> agentNumbers = Arrays.asList(agentNumber.split(","));
            payments =   paymentRepository.findAllByAgentNumberInAndPaymentDateBetween(agentNumbers, startDate, endDate);
        }else {
            payments =  paymentRepository.findAllByPaymentDateBetween( startDate, endDate);
        }
        return ResponseEntity.ok(new BaseResult(payments, "Payments fetched successfully", "00", 200));
    }

    @Override
    public void updatePremiumStatusForClosedShift(List<Long> primiumIds) {
        List<PremiumPayment> premiumPayments = paymentRepository.findAllByIdIn(primiumIds);
        // Update the commitStatus to "committed" for each PremiumPayment entity
        for (PremiumPayment payment : premiumPayments) {
            payment.setCommitStatus("COMMITTED");
        }
        // Save the updated PremiumPayment entities back to the database
        paymentRepository.saveAll(premiumPayments);
    }

    @Override
    public void updatePremiumStatusAfterCashUp(List<PremiumPayment> premiumPayments,String cashUpStatus) {
        // Update the commitStatus to "committed" for each PremiumPayment entity
        System.out.println("update status in premiums "+cashUpStatus);
        for (PremiumPayment payment : premiumPayments) {
            payment.setCommitStatus(cashUpStatus.toUpperCase());
            System.out.println(cashUpStatus.toUpperCase());
//            payment.setCommitStatus("APPROVED");
            System.out.println("after update "+payment.getCommitStatus());
        }

        // Save the updated PremiumPayment entities back to the database
        paymentRepository.saveAll(premiumPayments);
    }


    @Override
    public ResponseEntity<BaseResult> editsavePayment(PaymentRequestDash paymentRequest) {
        Optional<PremiumPayment> premiumPaymentOptional = paymentRepository.findById(paymentRequest.getId());
        PremiumPayment premiumPayment = premiumPaymentOptional.get();
        premiumPayment.setAgentNumber(paymentRequest.getAgentNumber());
        premiumPayment.setPayableAmount(paymentRequest.getPayableAmount());
        premiumPayment.setStatus(Status.ACTIVE);
        premiumPayment.setPolicyNumber(paymentRequest.getPolicyNumber());
        premiumPayment.setPaymentStatus(PaymentStatus.PAID);
        premiumPayment.setCurrency(paymentRequest.getCurrency());
        premiumPayment.setTransactionId(paymentRequest.getTransactionId());
        premiumPayment.setPayableMobileNumber(paymentRequest.getPayablePhoneNumber());
        premiumPayment.setPaymentOption(paymentRequest.getPaymentOption());
        premiumPayment.setSbu(paymentRequest.getSbu());
        premiumPayment.setLastModifiedDate(new Date());
        premiumPayment.setModifiedBy(paymentRequest.getModifiedBy());
        premiumPayment.setCreatedBy(paymentRequest.getAgentNumber());
        premiumPayment.setSubAreaOfficeName(paymentRequest.getSubAreaOfficeName());
        premiumPayment.setAreaOfficeName(paymentRequest.getAreaOfficeName());
        premiumPayment.setMobileNumber(paymentRequest.getMobileNumber());
        premiumPayment.setPolicyHolderFirstName(paymentRequest.getPolicyHolderFirstName());
        premiumPayment.setPolicyHolderLastName(paymentRequest.getPolicyHolderLastName());
        premiumPayment.setPolicyHolderContractPremium(paymentRequest.getPolicyHolderContractPremium());
        premiumPayment.setPolicyHolderIDNumber(paymentRequest.getPolicyHolderIDNumber());
        return ResponseEntity.ok(new BaseResult(paymentRepository.save(premiumPayment), "payment saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> alterPremium(PremiumPaymentForAlteration premiumPaymentForAlteration) {
        premiumPaymentForAlteration.setAlterationDate(new Date());
        Optional<PremiumPayment> premiumForAlteration = paymentRepository.findById(premiumPaymentForAlteration.getPremiumId());
        premiumForAlteration.get().setAlterationStatus("REQUESTED");
        paymentRepository.save(premiumForAlteration.get());
        return ResponseEntity.ok(new BaseResult(premiumAlterationRepository.save(premiumPaymentForAlteration), "altered payment saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> alterBankingDetails(BankedAmountsForAlteration bankedAmountsForAlteration) {
        bankedAmountsForAlteration.setAlterationDate(new Date());
        return ResponseEntity.ok(new BaseResult(bankingDetailsAlterationRepository.save(bankedAmountsForAlteration), "altered banking saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAlteredPremiumPayments() {
        return ResponseEntity.ok(new BaseResult(premiumAlterationRepository.findAll(), "fetched all altered payments successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getPaymentById(Long premiumId) {
        return ResponseEntity.ok(new BaseResult(paymentRepository.findById(premiumId), "fetched  payment successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> updateAlterarionByFinance(AlteredPayments alteredPayments) {
        Optional<PremiumPayment> oldPremium = paymentRepository.findById(alteredPayments.getPremiumId());
        Optional<PremiumPaymentForAlteration> newPremium = premiumAlterationRepository.findById(alteredPayments.getAlterationId());

        if(alteredPayments.getSelectedCurrencyStatus().equals("APPROVED")){
            oldPremium.get().setCurrency(newPremium.get().getCurrency());
            oldPremium.get().setPolicyHolderFirstName(alteredPayments.getFirstName());
            oldPremium.get().setPolicyHolderLastName(alteredPayments.getLastName());
        }
        if(alteredPayments.getSelectedPremiumStatus().equals("APPROVED")){
            oldPremium.get().setPayableAmount(newPremium.get().getPayableAmount());
            oldPremium.get().setPolicyHolderFirstName(alteredPayments.getFirstName());
            oldPremium.get().setPolicyHolderLastName(alteredPayments.getLastName());
        }
        oldPremium.get().setLastModifiedDate(new Date());
        oldPremium.get().setModifiedBy(alteredPayments.getFinanceApproverUsername());
        oldPremium.get().setAlterationStatus("RESPONDED");
        newPremium.get().setStatus("RESPONDED");
        premiumAlterationRepository.save(newPremium.get());
        return ResponseEntity.ok(new BaseResult(paymentRepository.save(oldPremium.get()), "altered  payment successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAlteredBankingDetails() {
        return ResponseEntity.ok(new BaseResult(bankingDetailsAlterationRepository.findAll(), "fetched all altered payments successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> checkPendingAlteration(String agentNumber) {
        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndAlterationStatus(agentNumber,"REQUESTED");
        return ResponseEntity.ok(new BaseResult(premiumPayment, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getPremiumsRejectedByFilter(String startDateStr, String endDateStr, String agentNumber, String username) throws ParseException {
        System.out.println("in service");
        List<PremiumPayment> payments ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        // Set the time part of the dates to midnight
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        startDate = cal.getTime();

        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        endDate = cal.getTime();
        if(!agentNumber.equals("all")) {
            List<String> agentNumbers = Arrays.asList(agentNumber.split(","));
            payments =   paymentRepository.findAllByAgentNumberInAndPaymentDateBetweenAndCommitStatus(agentNumbers, startDate, endDate,"REJECTED");
        }else {
            payments =  paymentRepository.findAllByPaymentDateBetweenAndCommitStatus( startDate, endDate,"REJECTED");
        }
        return ResponseEntity.ok(new BaseResult(payments, "Payments fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> getPremiumsAccountByFilter(String startDateStr, String endDateStr, String policyNumber, String username) throws ParseException {
        System.out.println("in service");
        List<PremiumPayment> payments ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        // Set the time part of the dates to midnight
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        startDate = cal.getTime();

        cal.setTime(endDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        endDate = cal.getTime();

        payments =  paymentRepository.findAllByPaymentDateBetweenAndPolicyNumber( startDate, endDate,policyNumber, Sort.by(Sort.Order.desc("id")));

        return ResponseEntity.ok(new BaseResult(payments, "Payments fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllFuneralOptions() {
        List<FuneralOption> funeralOptions = funeralOptionRepository.findAll();
        // Transform the list of FuneralOption objects to a list of strings
        List<String> optionNames = funeralOptions.stream()
                .map(FuneralOption::getName) // Assuming getName() returns the name of the option
                .collect(Collectors.toList());
        return ResponseEntity.ok(new BaseResult(optionNames, "Options fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveFuneralOptions(FuneralOption funeralOption) {

        return ResponseEntity.ok(new BaseResult(funeralOptionRepository.save(funeralOption), "saved  option successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllRejectedPaymentsByAgentNumber(String agentNumber) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);
        List<PremiumPayment> payments = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber,"REJECTED");
//        List<PremiumPayment> payments = paymentRepository.findAllByAgentNumber(agentNumber).stream()
//                .filter(p ->  isSameDate(p.getPaymentDate(), currentDate) ).collect(Collectors.toList());
        return ResponseEntity.ok(new BaseResult(payments, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumber(String agentNumber) {
        //        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumber(agentNumber);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);

        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber,"REJECTED");


        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") && p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashZIG = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH ZIG") )
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashZIG = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH ZIG"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalPaymentCashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") &&  !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        GrantPaymentResponse grantPaymentResponse = new GrantPaymentResponse();
        grantPaymentResponse.setAgentNumber(agentNumber);
        grantPaymentResponse.setTotalPaymentCashUSD(totalPaymentCashUSD);
        grantPaymentResponse.setTotalPaymentCashZIG(totalPaymentCashZIG);
        grantPaymentResponse.setTotalPaymentEcocashUSD(totalPaymentEcocashUSD);
        grantPaymentResponse.setTotalPaymentEcocashZIG(totalPaymentEcocashZIG);
        grantPaymentResponse.setTotalPaymentCashUSDFuneral(totalPaymentCashUSDFuneral);
        grantPaymentResponse.setTotalPaymentEcocashUSDFuneral(totalPaymentEcocashUSDFuneral);
        grantPaymentResponse.setPaymentDate(currentDate.substring(0,10));
        List<Long> premiumPaymentIds = premiumPayment.stream()
                .map(PremiumPayment::getId) // Assuming getId method exists in PremiumPayment
                .collect(Collectors.toList());
        grantPaymentResponse.setPrimiumIds(premiumPaymentIds);

        return ResponseEntity.ok(new BaseResult(grantPaymentResponse, "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumberAndSBU(String agentNumber, String sbu) {
        //        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumber(agentNumber);

        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndCommitStatus(agentNumber, "REJECTED");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);

        System.out.println("currentDate............");
        System.out.println(currentDate);

        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("CASH USD")  && p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("ECOCASH USD")  &&  p.getSbu().equals(sbu))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber) &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentCashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && p.getSbu().equals(sbu) && p.getPaymentDate().toLocalDate().equals(currentDate.toLocalDate()))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashZIG = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("CASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal totalPaymentEcocashUSD = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber) &&  p.getSbu().equals(sbu))
//                .map(PremiumPayment::getPayableAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalPaymentEcocashZIG = premiumPayment.stream()
                .filter(p -> p.getCurrency().equals("ECOCASH ZIG") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaymentCashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("CASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("CASH USD") &&  !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaymentEcocashUSDFuneral = premiumPayment.stream()
//                .filter(p -> p.getCurrency().equals("ECOCASH USD") && isSameDate(p.getPaymentDate(), currentDate) && p.getAgentNumber().equals(agentNumber))
                .filter(p -> p.getCurrency().equals("ECOCASH USD") && !p.getPaymentOption().equals("Premium"))
                .map(PremiumPayment::getPayableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        GrantPaymentResponse grantPaymentResponse = new GrantPaymentResponse();
        grantPaymentResponse.setAgentNumber(agentNumber);
        grantPaymentResponse.setTotalPaymentCashUSD(totalPaymentCashUSD);
        grantPaymentResponse.setTotalPaymentCashZIG(totalPaymentCashZIG);
        grantPaymentResponse.setTotalPaymentEcocashUSD(totalPaymentEcocashUSD);
        grantPaymentResponse.setTotalPaymentEcocashZIG(totalPaymentEcocashZIG);
        grantPaymentResponse.setPaymentDate(currentDate.substring(0,10));
        grantPaymentResponse.setTotalPaymentCashUSDFuneral(totalPaymentCashUSDFuneral);
        grantPaymentResponse.setTotalPaymentEcocashUSDFuneral(totalPaymentEcocashUSDFuneral);

        List<Long> premiumPaymentIds = premiumPayment.stream()
                .map(PremiumPayment::getId) // Assuming getId method exists in PremiumPayment
                .collect(Collectors.toList());
        grantPaymentResponse.setPrimiumIds(premiumPaymentIds);
        return ResponseEntity.ok(new BaseResult(grantPaymentResponse, "payment fetched successfully","00",200));
    }

    @Override
    public BaseResult findConsistentPolicyHoldersInLastFourMonths() {
        LocalDate currentMonthStart = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate sixMonthsAgoLocalDate = currentMonthStart.minusMonths(5);

        Date sixMonthsAgoDate = Date.from(sixMonthsAgoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("----- Debugging findConsistentPolicyHoldersInLastFourMonths -----");
        System.out.println("Current Date: " + LocalDate.now());
        System.out.println("Calculated start date for query (fourMonthsAgoDate): " + sixMonthsAgoDate);
        System.out.println("System Default TimeZone: " + ZoneId.systemDefault());

        // Step 1: Find all distinct policyNumbers that have made *any* payments
        // within the last four months. This query now explicitly excludes NULL and empty strings.
//        List<String> potentialPolicyNumbers = paymentRepository.findDistinctPolicyNumbersWithPaymentsInLastFourMonths(
//                fourMonthsAgoDate);

        List<String> potentialPolicyNumbers = paymentRepository.findDistinctPolicyNumbersWithPaymentsInLastSixMonths(
                sixMonthsAgoDate);

        System.out.println("Potential Policy Numbers fetched by query (excluding null/empty): " + potentialPolicyNumbers);

        if (potentialPolicyNumbers.isEmpty()) {
            System.out.println("No potential policy numbers found in the initial broad query.");
            return new BaseResult(null, "No policy holders found with payments in the last four months", "01", 200);
        }

        // Step 2: Fetch all payments for these potential policy numbers
        // This query also explicitly excludes NULL and empty strings.
        List<PremiumPayment> allRelevantPayments = paymentRepository.findAllPaymentsForPolicyNumbersInLastSixMonths(
                potentialPolicyNumbers, sixMonthsAgoDate);

        System.out.println("Total relevant payments fetched for potential policy numbers (excluding null/empty): " + allRelevantPayments.size());

        Map<String, List<PremiumPayment>> paymentsByPolicyNumber = allRelevantPayments.stream()
                .collect(Collectors.groupingBy(PremiumPayment::getPolicyNumber));

        List<PolicyHolderPaymentDetailsDTO> consistentPolicyHolders = new ArrayList<>();

        for (String policyNumber : potentialPolicyNumbers) {
            List<PremiumPayment> policyHolderPayments = paymentsByPolicyNumber.getOrDefault(policyNumber, Collections.emptyList());

            System.out.println("Checking consistency for Policy Number: " + policyNumber + " with " + policyHolderPayments.size() + " payments.");
            if (!policyHolderPayments.isEmpty()) {
                policyHolderPayments.forEach(p -> System.out.println("\tPayment: " + p.getCreationDate()));
            }

            if (isConsistentPayer(policyHolderPayments)) {
                consistentPolicyHolders.add(mapToPolicyHolderPaymentDetailsDTO(policyHolderPayments));
                System.out.println("Policy Number: " + policyNumber + " is CONSISTENT.");
            } else {
                System.out.println("Policy Number: " + policyNumber + " is NOT consistent.");
            }
        }

        System.out.println("Found " + consistentPolicyHolders.size() + " consistent policy holders.");

        if (consistentPolicyHolders.isEmpty()) {
            return new BaseResult(null, "No consistent policy holders found for the past four months based on *any* payments.", "02", 200);
        }
        return new BaseResult(consistentPolicyHolders, "Consistent policy holders retrieved successfully", "00", 200);
    }

    @Override
    public BaseResult getConsistentPolicyHoldersLastSixMonths(String place) {
        LocalDate currentMonthStart = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate sixMonthsAgoLocalDate = currentMonthStart.minusMonths(5);

        Date sixMonthsAgoDate = Date.from(sixMonthsAgoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("----- Debugging findConsistentPolicyHoldersInLastFourMonths -----");
        System.out.println("Current Date: " + LocalDate.now());
        System.out.println("Calculated start date for query (fourMonthsAgoDate): " + sixMonthsAgoDate);
        System.out.println("System Default TimeZone: " + ZoneId.systemDefault());
        List<String> potentialPolicyNumbers = paymentRepository.findDistinctPolicyNumbersWithPaymentsInLastSixMonths(
                sixMonthsAgoDate);

        System.out.println("Potential Policy Numbers fetched by query (excluding null/empty): " + potentialPolicyNumbers);

        if (potentialPolicyNumbers.isEmpty()) {
            System.out.println("No potential policy numbers found in the initial broad query.");
            return new BaseResult(null, "No policy holders found with payments in the last four months", "01", 200);
        }

        // Step 2: Fetch all payments for these potential policy numbers
        // This query also explicitly excludes NULL and empty strings.
        List<PremiumPayment> allRelevantPayments = paymentRepository.findAllPaymentsForPolicyNumbersInLastSixMonths(
                potentialPolicyNumbers, sixMonthsAgoDate);

        System.out.println("Total relevant payments fetched for potential policy numbers (excluding null/empty): " + allRelevantPayments.size());

        Map<String, List<PremiumPayment>> paymentsByPolicyNumber = allRelevantPayments.stream()
                .collect(Collectors.groupingBy(PremiumPayment::getPolicyNumber));

        List<PolicyHolderPaymentDetailsDTO> consistentPolicyHolders = new ArrayList<>();

        for (String policyNumber : potentialPolicyNumbers) {
            List<PremiumPayment> policyHolderPayments = paymentsByPolicyNumber.getOrDefault(policyNumber, Collections.emptyList());

            System.out.println("Checking consistency for Policy Number: " + policyNumber + " with " + policyHolderPayments.size() + " payments.");
            if (!policyHolderPayments.isEmpty()) {
                policyHolderPayments.forEach(p -> System.out.println("\tPayment: " + p.getCreationDate()));
            }

            if (isConsistentPayer(policyHolderPayments)) {
                consistentPolicyHolders.add(mapToPolicyHolderPaymentDetailsDTO(policyHolderPayments));
                System.out.println("Policy Number: " + policyNumber + " is CONSISTENT.");
            } else {
                System.out.println("Policy Number: " + policyNumber + " is NOT consistent.");
            }
        }

        System.out.println("Found " + consistentPolicyHolders.size() + " consistent policy holders.");

        if (consistentPolicyHolders.isEmpty()) {
            return new BaseResult(null, "No consistent policy holders found for the past four months based on *any* payments.", "02", 200);
        }
        return new BaseResult(consistentPolicyHolders, "Consistent policy holders retrieved successfully", "00", 200);
    }

    private boolean isConsistentPayer(List<PremiumPayment> payments) {
        if (payments.isEmpty()) {
            return false;
        }

        Set<String> distinctMonthYearsPaid = payments.stream()
                .map(payment -> {
                    LocalDate paymentDate = payment.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return paymentDate.getYear() + "-" + String.format("%02d", paymentDate.getMonthValue());
                })
                .collect(Collectors.toSet());

        System.out.println("\tDistinct Month-Years Paid by this Policy Number: " + distinctMonthYearsPaid);

        LocalDate currentMonthStart = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        for (int i = 0; i < 6; i++) {
            LocalDate monthToCheck = currentMonthStart.minusMonths(i);
            String monthYearToCheck = monthToCheck.getYear() + "-" + String.format("%02d", monthToCheck.getMonthValue());

            System.out.println("\tChecking for required month-year: " + monthYearToCheck);

            if (!distinctMonthYearsPaid.contains(monthYearToCheck)) {
                System.out.println("\tMISSING payment for: " + monthYearToCheck);
                return false;
            }
        }
        System.out.println("\tAll 6 required months found for this Policy Number.");
        return true;
    }

    private PolicyHolderPaymentDetailsDTO mapToPolicyHolderPaymentDetailsDTO(List<PremiumPayment> payments) {
        if (payments == null || payments.isEmpty()) {
            return null;
        }


        PremiumPayment firstPayment = payments.get(0);
        PolicyHolderPaymentDetailsDTO dto = new PolicyHolderPaymentDetailsDTO();
//        dto.setPolicyHolderId(firstPayment.getPolicyHolderId());
        dto.setPolicyHolderFirstName(firstPayment.getPolicyHolderFirstName());
        dto.setPolicyHolderLastName(firstPayment.getPolicyHolderLastName());
        dto.setPolicyHolderIDNumber(firstPayment.getPolicyHolderIDNumber() != null ? firstPayment.getPolicyHolderIDNumber():"");
        dto.setPolicyNumber(firstPayment.getPolicyNumber());
        dto.setMobileNumber(firstPayment.getMobileNumber() != null ? firstPayment.getMobileNumber():"");
        dto.setPolicyHolderContractPremium(firstPayment.getPolicyHolderContractPremium());

       // List<PolicyHolder> policyHolder = policyHolderRepository.findAllByPolicyNumber(firstPayment.getPolicyNumber());
//        System.out.println(policyHolder.get(0).getPhoneNumber() != null ? policyHolder.get(0).getPhoneNumber() : "0");
//        dto.setContact(policyHolder.get(0).getPhoneNumber() != null ? policyHolder.get(0).getPhoneNumber() : "0");

        List<PaymentTransactionDTO> transactionDTOs = payments.stream()
                .sorted(Comparator.comparing(PremiumPayment::getCreationDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(6)
                .map(payment -> {
                    String payableMobileNumber = payment.getMobileNumber() != null ?
                            policyHolderRepository.findFirstByPolicyNumber(payment.getPolicyNumber())
                                    .map(ph -> ph.getLastName()) // Correctly call getPhoneNumber on PolicyHolder
                                    .orElse(null) : null;

                    return new PaymentTransactionDTO(
                            payment.getId(),
                            payment.getPayableAmount(),
                            payment.getPaymentDate(),
                            payment.getPaymentStatus() != null ? payment.getPaymentStatus().name() : null,
                            payableMobileNumber
                    );
                })
                .collect(Collectors.toList());
        dto.setTransactions(transactionDTOs);

        return dto;
    }

    @Override
    public ResponseEntity<BaseResult> getAllAlteredPremiumPaymentsByAgent(String agentNumber) {
        return ResponseEntity.ok(new BaseResult(premiumAlterationRepository.findAllByAgentNumberAndType(agentNumber,"PREMIUM PAYMENT"), "fetched all altered payments successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAlteredBankedDetailsPaymentsByAgent(String agentNumber) {
        return ResponseEntity.ok(new BaseResult(bankingDetailsAlterationRepository.findAllByAgentNumberAndType(agentNumber,"BANKING DETAILS"), "fetched all banked payments successfully","00",200));
    }

    private static Date removeTimeFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static boolean isSameDate(Date date1, String date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(removeTimeFromDate(date1));
        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(removeTimeFromDate(date2));

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }


    private String cleanString(String s) {
        if (s == null) return "";
        return s.trim().replace("\"", "");
    }

    @Transactional
    public String updatePolicyNumbersFromFile(MultipartFile file) throws IOException {

        List<PolicyUpdateEntry> updates = parseFile(file);

        if (updates.isEmpty()) {
            return "File parsed successfully, but no valid ID/PolicyNumber records were extracted. Please ensure your file has two comma-separated columns with ID first and Policy Number second. ❌";
        }

        // 2. Extract all IDs for efficient batch lookup
        List<Long> idsToUpdate = updates.stream()
                .map(PolicyUpdateEntry::getId)
                .toList();

        // 3. Fetch all existing records
        List<PremiumPayment> existingPayments = paymentRepository.findAllByIdIn(idsToUpdate);
        Map<Long, PremiumPayment> paymentMap = existingPayments.stream()
                .collect(Collectors.toMap(PremiumPayment::getId, p -> p));

        // 4. Update and track results
        List<PremiumPayment> paymentsToSave = new ArrayList<>();
        int foundCount = 0;
        int notFoundCount = 0;

        for (PolicyUpdateEntry entry : updates) {
            PremiumPayment payment = paymentMap.get(entry.getId());

            if (payment != null) {
                payment.setPolicyNumber(entry.getPolicyNumber());
                paymentsToSave.add(payment);
                foundCount++;
            } else {
                notFoundCount++;
            }
        }

        // 5. Batch save the updated records
        paymentRepository.saveAll(paymentsToSave);

        return String.format(
                "✅ Update complete! Successfully updated %d records. %d records from the file were not found in the database.",
                foundCount,
                notFoundCount
        );
    }

    @Override
    public ResponseEntity<BaseResult> getPaymentsByPolicy(String policy) {
        List<PremiumPayment> payments = paymentRepository.findAllByPolicyNumber(policy);

        return ResponseEntity.ok(new BaseResult(payments, "payment fetched successfully","00",200));
    }

    /**
     * Reads the file, explicitly assuming the first column (index 0) is ID and the second (index 1) is Policy Number.
     * It skips the first row, regardless of its content (treating it as an ignorable header).
     */
    private List<PolicyUpdateEntry> parseFile(MultipartFile file) throws IOException {
        List<PolicyUpdateEntry> updates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Explicitly skip the first row (Header/Title)
            if (reader.ready()) {
                reader.readLine();
            }

            String currentLine;
            // Process all subsequent lines as data
            while ((currentLine = reader.readLine()) != null) {
                // Assuming comma-separated values (CSV)
                String[] dataParts = currentLine.split(",");

                // We need at least 2 columns (index 0 and index 1)
                if (dataParts.length >= 2) {
                    try {
                        // Extract and clean values using the fixed indices
                        // Index 0: ID
                        Long id = Long.parseLong(cleanString(dataParts[0]));

                        // Index 1: Policy Number (force uppercase for consistency)
                        String policyNumber = cleanString(dataParts[1]).toUpperCase();

                        updates.add(new PolicyUpdateEntry(id, policyNumber));

                    } catch (NumberFormatException e) {
                        System.err.println("Skipping line: First column is not a valid ID number in line: " + currentLine);
                    }
                } else {
                    System.err.println("Skipping line: Not enough columns (expected at least 2) in line: " + currentLine);
                }
            }
        }
        return updates;
    }
}


