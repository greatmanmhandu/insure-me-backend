package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Bank;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.BankedAmountsForAlteration;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.FuneralOption;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AlteredPayments;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequestDash;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.BankService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankController {

    private final BankService bankService;



    @GetMapping("getAllBankDetails")
    ResponseEntity<BaseResult> getAllBankDetails() {
        return bankService.getAllBankDetails();
    }

    @PostMapping("saveBankDetails")
    ResponseEntity<BaseResult> saveBankDetails(@RequestBody Bank bank) {
        return bankService.saveBankDetails(bank);
    }


}
