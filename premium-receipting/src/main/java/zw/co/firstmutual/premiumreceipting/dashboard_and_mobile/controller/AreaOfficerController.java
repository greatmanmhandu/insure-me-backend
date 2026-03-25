package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AreaOfficerRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AgentService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AreaOfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AreaOfficerController {

    private final AreaOfficerService areaOfficerService;

    @GetMapping("getAllAreaOfficers")
    ResponseEntity<BaseResult> getAllAreaOfficers ()
    {
        return areaOfficerService.getAllAreaOfficers();
    }
    @GetMapping("getAreaOfficerByAreaOfficerNumber")
    ResponseEntity<BaseResult> getAreaOfficerByAreaOfficerNumber(@RequestParam("areaOfficerNumber") String areaOfficerNumber) {
        return areaOfficerService.getAreaOfficerByAreaOfficerNumber(areaOfficerNumber);
    }

    @PostMapping("saveAreaOfficer")
    ResponseEntity<BaseResult> saveAreaOfficer(@RequestBody AreaOfficerRequest areaOfficerRequest)
    {
        return areaOfficerService.saveAreaOfficer(areaOfficerRequest);
    }



    @GetMapping("getAreaOfficerById")
    ResponseEntity<BaseResult> findAreaOfficerById (@RequestParam Long id)
    {
        return areaOfficerService.findAreaOfficerById(id);
    }

}

