package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SWUpdates;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AgentService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl.ExcelDataService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.SWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*")
public class SWUpdatesController {

    private final SWService swService;

    @GetMapping("get-sw-updates")
    ResponseEntity<BaseResult> getUpdates ()
    {
        return swService.getUpdates();
    }


    @PostMapping("save-sw-updates")
    ResponseEntity<BaseResult> saveUpdates(@RequestBody SWUpdates swUpdates)
    {
        return swService.saveUpdates(swUpdates);
    }
}

