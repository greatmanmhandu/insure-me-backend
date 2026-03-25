package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.Bank;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.repository.BankRepository;
import com.vozhe.jwt.dashboard_and_mobile.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    @Override
    public ResponseEntity<BaseResult> saveBankDetails(Bank bank) {
        return ResponseEntity.ok(new BaseResult(bankRepository.save(bank), "payment fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllBankDetails() {
        return ResponseEntity.ok(new BaseResult(bankRepository.findAll(), "payment fetched successfully","00",200));
    }
}
