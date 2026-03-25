package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.*;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AlteredPayments;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequestDash;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface BankService {


    ResponseEntity<BaseResult> saveBankDetails(Bank bank);

    ResponseEntity<BaseResult> getAllBankDetails();
}
