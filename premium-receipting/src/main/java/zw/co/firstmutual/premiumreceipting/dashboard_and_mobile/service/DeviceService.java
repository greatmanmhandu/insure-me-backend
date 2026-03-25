package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.MobileDevice;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Printer;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import org.springframework.http.ResponseEntity;

public interface DeviceService {


    ResponseEntity<BaseResult> getAllPrinters();

    ResponseEntity<BaseResult> getAllMobileDevice();

    ResponseEntity<BaseResult> savePrinter(Printer printer);

    ResponseEntity<BaseResult> saveMobileDevice(MobileDevice mobileDevice);

    Printer findPrinterById(Long id);

    MobileDevice findMobileDeviceById(Long id);
}


