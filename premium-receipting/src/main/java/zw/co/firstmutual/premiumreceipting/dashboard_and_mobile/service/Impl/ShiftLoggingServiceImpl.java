package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPayment;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.PaymentRepository;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.ShiftLoggingRepository;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.PaymentService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.ShiftLogingService;
import jnr.a64asm.Shift;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftLoggingServiceImpl implements ShiftLogingService {

    private final ShiftLoggingRepository shiftLoggingRepository;
    private final PaymentRepository paymentRepository;

    private final PaymentService paymentService;
    @Override
    public ResponseEntity<BaseResult> getAllShifts() {
        return ResponseEntity.ok(new BaseResult(shiftLoggingRepository.findAllByOrderByIdDesc(), "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftsByUsername(String username) {
        return ResponseEntity.ok(new BaseResult(shiftLoggingRepository.findAllByUsername(username), "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveShift(ShiftLogin shiftLogin) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);
        shiftLogin.setCreationDate(new Date());
        shiftLogin.setStartDateTime(new Date());
        shiftLogin.setStartDate(currentDate.substring(0,10));
        shiftLogin.setStartTime(currentDate.substring(11,19));
        ShiftLogin savedShift = shiftLoggingRepository.save(shiftLogin);
        return ResponseEntity.ok(new BaseResult(savedShift, "shift saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftByAgentId(Long agentId) {
        return ResponseEntity.ok(new BaseResult(shiftLoggingRepository.findAllByAgentId(agentId), "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> closeShift(ShiftLogin shiftLogin) {
        ShiftLogin savedShift = new ShiftLogin();
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findById(shiftLogin.getId());
        if (optionalShiftLogin.isPresent()) {
            ShiftLogin oldShiftLogin = optionalShiftLogin.get();
            oldShiftLogin.setEndDateTime(new Date());
            oldShiftLogin.setCashUpStatus("Requested");
            oldShiftLogin.setTenderUserName(shiftLogin.getTenderUserName());
            oldShiftLogin.setTenderedName(shiftLogin.getTenderedName());
            oldShiftLogin.setTotalPaymentCashUSD(shiftLogin.getTotalPaymentCashUSD());
            oldShiftLogin.setTotalPaymentCashZIG(shiftLogin.getTotalPaymentCashZIG());
            oldShiftLogin.setTotalPaymentEcocashUSD(shiftLogin.getTotalPaymentEcocashUSD());
            oldShiftLogin.setTotalPaymentEcocashZIG(shiftLogin.getTotalPaymentEcocashZIG());
            oldShiftLogin.setTotalPaymentCashUSDFuneral(shiftLogin.getTotalPaymentCashUSDFuneral());
            oldShiftLogin.setTotalPaymentEcocashUSDFuneral(shiftLogin.getTotalPaymentEcocashUSDFuneral());
            savedShift = shiftLoggingRepository.save(oldShiftLogin);
            paymentService.updatePremiumStatusForClosedShift(shiftLogin.getPremiumIds());
        } else {
            // Handle the case when the ShiftLogin object with the given ID is not found
        }
        return ResponseEntity.ok(new BaseResult(savedShift, "shift closed successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getPendingShiftByAgentNumber(String agentNumber) {
        ShiftLogin savedShift = new ShiftLogin();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);
        currentDate = currentDate.substring(0,10);
        String savedDate = "";
        System.out.println(currentDate);
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findByAgentNumberAndStartDate(agentNumber,currentDate);
        System.out.println(optionalShiftLogin);
        if (optionalShiftLogin.isPresent()) {
            savedShift = optionalShiftLogin.get();
            savedShift.setErrorMsg("FOUND");
        }else {
            savedShift.setErrorMsg("NOT FOUND");
        }
        return ResponseEntity.ok(new BaseResult(savedShift, "shift fetched successfully","00",200));
    }


    public ShiftLogin getPendingShiftByAgentNumberSecond(String agentNumber) {
        ShiftLogin savedShift = new ShiftLogin();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDate = LocalDateTime.now().format(dateFormatter);
        currentDate = currentDate.substring(0,10);
        String savedDate = "";
        System.out.println(currentDate);
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findByAgentNumberAndStartDate(agentNumber,currentDate);
        System.out.println(optionalShiftLogin);
        if (optionalShiftLogin.isPresent()) {
            savedShift = optionalShiftLogin.get();
            savedShift.setErrorMsg("FOUND");
        }else {
            savedShift.setErrorMsg("NOT FOUND");
        }
        return savedShift;
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftsByTenderUsername(String tenderUserName) {
        return ResponseEntity.ok(new BaseResult(shiftLoggingRepository.findAllByTenderUserNameAndEndDateTimeNotNull(tenderUserName), "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> updateShift(ShiftLogin shiftLogin) {
        ShiftLogin savedShift = new ShiftLogin();
        System.out.println("bank date " +shiftLogin.getBankedDate());
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findById(shiftLogin.getId());
        if (optionalShiftLogin.isPresent()) {
            ShiftLogin oldShiftLogin = optionalShiftLogin.get();
            oldShiftLogin.setBankName(shiftLogin.getBankName());
            oldShiftLogin.setAccountNumber(shiftLogin.getAccountNumber());
            oldShiftLogin.setTransactionRef(shiftLogin.getTransactionRef());
            oldShiftLogin.setTotalBankedAmountUSD(shiftLogin.getTotalBankedAmountUSD());
            oldShiftLogin.setSystemRecordedBankDate(new Date());
            oldShiftLogin.setFinanceStatus("PENDING");
            oldShiftLogin.setDeviation(shiftLogin.getDeviation());
            oldShiftLogin.setRemarks(shiftLogin.getRemarks());
            oldShiftLogin.setBankedDate(shiftLogin.getBankedDate());

            savedShift = shiftLoggingRepository.save(oldShiftLogin);
        } else {
            // Handle the case when the ShiftLogin object with the given ID is not found
        }
        return ResponseEntity.ok(new BaseResult(savedShift, "shift update successfully","00",200));
    }



    public ResponseEntity<BaseResult> updateShiftStatus(ShiftLogin shiftLogin) {
        ShiftLogin savedShift = new ShiftLogin();
        System.out.println("shiftLogin id " +shiftLogin.getId());
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findById(shiftLogin.getId());
        List<PremiumPayment> premiumPayment = paymentRepository.findAllByAgentNumberAndCommitStatus(optionalShiftLogin.get().getAgentNumber(),"COMMITTED");
        System.out.println("shiftLogin.getAgentNumber()" +shiftLogin.getAgentNumber());
        for (PremiumPayment payment : premiumPayment) {
            System.out.println(payment.getId());
        }
        if (optionalShiftLogin.isPresent()) {
            System.out.println("am now updating shift status to " +shiftLogin.getCashUpStatus());
            ShiftLogin oldShiftLogin = optionalShiftLogin.get();
            oldShiftLogin.setCashUpStatus(shiftLogin.getCashUpStatus());
            savedShift = shiftLoggingRepository.save(oldShiftLogin);
            paymentService.updatePremiumStatusAfterCashUp(premiumPayment,shiftLogin.getCashUpStatus());
        } else {
            // Handle the case when the ShiftLogin object with the given ID is not found
        }
        return ResponseEntity.ok(new BaseResult(savedShift, "shift update successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getShiftesByFilter(String startDateStr, String endDateStr, String agentNumber, String username) throws ParseException {
        System.out.println("in service");
        List<ShiftLogin> shifts ;
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
            shifts =   shiftLoggingRepository.findAllByAgentNumberInAndEndDateTimeBetween(agentNumbers, startDate, endDate);
        }else {
            shifts =  shiftLoggingRepository.findAllByEndDateTimeBetween( startDate, endDate);
        }
        return ResponseEntity.ok(new BaseResult(shifts, "Payments fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> updateApproveFinance(ShiftLogin shiftLogin) {
        ShiftLogin savedShift = new ShiftLogin();
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findById(shiftLogin.getId());
        if (optionalShiftLogin.isPresent()) {
            ShiftLogin oldShiftLogin = optionalShiftLogin.get();
            oldShiftLogin.setFinanceStatus(shiftLogin.getFinanceStatus());
            oldShiftLogin.setFinanceApprovedDate(new Date());
            oldShiftLogin.setFinanceApproverName(shiftLogin.getFinanceApproverName());
            oldShiftLogin.setFinanceApproverUsername(shiftLogin.getFinanceApproverUsername());
            savedShift = shiftLoggingRepository.save(oldShiftLogin);
        } else {
            // Handle the case when the ShiftLogin object with the given ID is not found
        }
        return ResponseEntity.ok(new BaseResult(savedShift, "shift update successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftsByTender(String tenderUserName) {
        return ResponseEntity.ok(new BaseResult(shiftLoggingRepository.findAllByTenderUserName(tenderUserName), "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> consolidate(ShiftLogin shiftLogin) {
        List<ShiftLogin> shiftLoginList = shiftLoggingRepository.findAllById(shiftLogin.getPremiumIds());

        for (ShiftLogin login : shiftLoginList) {
            login.setConsolidationStatus("CONSOLIDATED");
        }
        shiftLoggingRepository.saveAll(shiftLoginList);

        ShiftLogin shiftLoginToConsolidate = new ShiftLogin();
        shiftLoginToConsolidate.setCreationDate(new Date());
        shiftLoginToConsolidate.setTotalConsolidatedUSD(shiftLogin.getTotalConsolidatedUSD());
        shiftLoginToConsolidate.setConsolidatedByUsername(shiftLogin.getConsolidatedByUsername());
        shiftLoginToConsolidate.setConsolidatedByName(shiftLogin.getConsolidatedByName());
        shiftLoginToConsolidate.setTotalBankedAmountUSD(shiftLogin.getTotalConsolidatedUSD());
        shiftLoginToConsolidate.setTenderUserName(shiftLogin.getConsolidatedByUsername());
        shiftLoginToConsolidate.setCashUpStatus("Approved");
        shiftLoginToConsolidate.setSbu(shiftLogin.getSbu());
        shiftLoginToConsolidate.setAreaOfficeName(shiftLogin.getAreaOfficeName());
        shiftLoginToConsolidate.setTenderedName(shiftLogin.getConsolidatedByName());
        shiftLoginToConsolidate.setUsername(shiftLogin.getConsolidatedByUsername());
        shiftLoginToConsolidate.setTotalPaymentCashUSD(shiftLogin.getTotalConsolidatedUSD());
        shiftLoginToConsolidate.setAgentNumber(shiftLogin.getConsolidatedByUsername());
        shiftLoginToConsolidate.setCreatedBy(shiftLogin.getConsolidatedByUsername());
        shiftLoginToConsolidate.setStartDate(String.valueOf(new Date()).substring(0,8));
        shiftLoginToConsolidate.setEndDateTime(new Date());
        shiftLoginToConsolidate.setConsolidationStatus("CONSOLIDATEDREC");

        shiftLoggingRepository.save(shiftLoginToConsolidate);

        return ResponseEntity.ok(new BaseResult(new ShiftLogin(), "shift consolidated successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getShiftById(long id) {
        Optional<ShiftLogin> optionalShiftLogin = shiftLoggingRepository.findById(id);
        return ResponseEntity.ok(new BaseResult(optionalShiftLogin, "shift fetched successfully","00",200));
    }



    public ResponseEntity<BaseResult> getAllShiftByParams(
            String cashUpStatus,
            String consolidationStatus,
            String sbu) {

        List<ShiftLogin> shifts = shiftLoggingRepository.findAll(); // Fetch all shifts

        // Apply filters based on the parameters
        if (cashUpStatus != null) {
            shifts = shifts.stream()
                    .filter(shift -> cashUpStatus.equals(shift.getCashUpStatus())) // Safe null check
                    .collect(Collectors.toList());
        }
        if (consolidationStatus != null) {
            shifts = shifts.stream()
                    .filter(shift -> consolidationStatus.equals(shift.getConsolidationStatus())) // Safe null check
                    .collect(Collectors.toList());
        }
        if (sbu != null) {
            shifts = shifts.stream()
                    .filter(shift -> sbu.equals(shift.getSbu())) // Safe null check
                    .collect(Collectors.toList());
        }

        // Filter for bank name
        shifts = shifts.stream()
                .filter(shift -> shift.getBankName() == null || shift.getBankName().isEmpty())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BaseResult(shifts, "Shift fetched successfully", "00", 200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftsPending() {
        List<ShiftLogin> results = shiftLoggingRepository.findAllByFinanceStatusAndCashUpStatusAndConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(
                "Pending", "Approved", "CONSOLIDATED", BigDecimal.ZERO
        );
        System.out.println("getAllShiftsPending");
        return ResponseEntity.ok(new BaseResult(results, "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllShiftsWithMissingBankedDetails() {
        System.out.println("getAllShiftsWithMissingBankedDetails");
        List<ShiftLogin> results = shiftLoggingRepository.findAllByCashUpStatusNotAndTotalPaymentCashUSDGreaterThan(
                "Approved", BigDecimal.ZERO
        );
        return ResponseEntity.ok(new BaseResult(results, "shifts fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getFinanceBankingDetails() {
        List<ShiftLogin> results = shiftLoggingRepository.findAllByFinanceStatusAndCashUpStatusAndConsolidationStatusNotAndTotalPaymentCashUSDGreaterThan(
                "APPROVED", "Approved", "CONSOLIDATED", BigDecimal.ZERO
        );
        System.out.println("for finance.............");
        return ResponseEntity.ok(new BaseResult(results, "shifts fetched successfully","00",200));
    }
}


