package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AlteredPayments;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.PaymentRequestDash;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface BankService {


    ResponseEntity<BaseResult> saveBankDetails(Bank bank);

    ResponseEntity<BaseResult> getAllBankDetails();
}

