package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.models.Bank;
import com.vozhe.jwt.dashboard_and_mobile.models.BankedAmountsForAlteration;
import com.vozhe.jwt.dashboard_and_mobile.models.FuneralOption;
import com.vozhe.jwt.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AlteredPayments;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequestDash;
import com.vozhe.jwt.dashboard_and_mobile.service.BankService;
import com.vozhe.jwt.dashboard_and_mobile.service.PaymentService;
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