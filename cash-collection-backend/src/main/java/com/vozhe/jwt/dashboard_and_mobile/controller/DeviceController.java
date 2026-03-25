package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.models.MobileDevice;
import com.vozhe.jwt.dashboard_and_mobile.models.Printer;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("getAllPrinters")
    ResponseEntity<BaseResult> getAllPrinters ()
    {
        return deviceService.getAllPrinters();
    }

    @GetMapping("getAllMobileDevice")
    ResponseEntity<BaseResult> getAllMobileDevice ()
    {
        return deviceService.getAllMobileDevice();
    }


    @PostMapping("savePrinter")
    ResponseEntity<BaseResult> savePrinter(@RequestBody Printer printer)
    {
        return deviceService.savePrinter(printer);
    }

    @PostMapping("saveMobileDevice")
    ResponseEntity<BaseResult> saveMobileDevice(@RequestBody MobileDevice mobileDevice)
    {
        return deviceService.saveMobileDevice(mobileDevice);
    }


}
