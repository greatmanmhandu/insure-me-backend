package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.MobileDevice;
import com.vozhe.jwt.dashboard_and_mobile.models.Printer;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.repository.MobileDeviceRepository;
import com.vozhe.jwt.dashboard_and_mobile.repository.PrinterRepository;
import com.vozhe.jwt.dashboard_and_mobile.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final PrinterRepository printerRepository;
    private final MobileDeviceRepository mobileDeviceRepository;


    @Override
    public ResponseEntity<BaseResult> getAllPrinters() {
        return ResponseEntity.ok(new BaseResult(printerRepository.findAll(), "Printers fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllMobileDevice() {
        return ResponseEntity.ok(new BaseResult(mobileDeviceRepository.findAll(), "Devices fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> savePrinter(Printer printer) {
        return ResponseEntity.ok(new BaseResult(printerRepository.save(printer), "printer saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveMobileDevice(MobileDevice mobileDevice) {
        return ResponseEntity.ok(new BaseResult(mobileDeviceRepository.save(mobileDevice), "mobile saved successfully","00",200));
    }

    @Override
    public Printer findPrinterById(Long id) {
        return printerRepository.findById(id).get();
    }

    @Override
    public MobileDevice findMobileDeviceById(Long id) {
        return mobileDeviceRepository.findById(id).get();
    }
}

