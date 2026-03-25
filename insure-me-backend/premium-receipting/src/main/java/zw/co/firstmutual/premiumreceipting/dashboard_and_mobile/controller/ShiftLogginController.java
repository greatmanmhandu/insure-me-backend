package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.ShiftLogingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShiftLogginController {

    private final ShiftLogingService shiftLogingService;

    @GetMapping("getAllShifts")
    ResponseEntity<BaseResult> getAllShifts ()
    {
        return shiftLogingService.getAllShifts();
    }

    @GetMapping("finance-banking-details")
    ResponseEntity<BaseResult> getFinanceBankingDetails ()
    {
        return shiftLogingService.getFinanceBankingDetails();
    }

    @GetMapping("pending-finance-approval")
    ResponseEntity<BaseResult> getAllShiftsPending ()
    {
        return shiftLogingService.getAllShiftsPending();
    }

    @GetMapping("cash-up-bit-missing-banked-details")
    ResponseEntity<BaseResult> getAllShiftsWithMissingBankedDetails ()
    {
        return shiftLogingService.getAllShiftsWithMissingBankedDetails();
    }

    @GetMapping("getAllShifts-By-Params")
    public ResponseEntity<BaseResult> getAllShiftByParams(
            @RequestParam(required = false) String cashUpStatus,
            @RequestParam(required = false) String consolidationStatus,
            @RequestParam(required = false) String sbu
    ) {

        return shiftLogingService.getAllShiftByParams(cashUpStatus, consolidationStatus, sbu);
    }

    @GetMapping("getAllShiftsByUsername")
    ResponseEntity<BaseResult> getHires(@RequestParam("username") String username) {
        return shiftLogingService.getAllShiftsByUsername(username);
    }

    @GetMapping("getAllShiftsByTender/{username}")
    ResponseEntity<BaseResult> getAllShiftsByTender(@PathVariable("username") String username) {
        return shiftLogingService.getAllShiftsByTender(username);
    }

    @GetMapping("getAllShiftsByTenderUsername")
    ResponseEntity<BaseResult> getAllShiftsByTenderUsername(@RequestParam("tenderUserName") String tenderUserName) {
        return shiftLogingService.getAllShiftsByTenderUsername(tenderUserName);
    }


//    @GetMapping("getAllShiftsByTenderAnd")
//    ResponseEntity<BaseResult> getAllShiftsByTenderUsername(@RequestParam("tenderUserName") String tenderUserName) {
//        return shiftLogingService.getAllShiftsByTenderUsername(tenderUserName);
//    }

    @PostMapping("saveShift")
    ResponseEntity<BaseResult> saveShift(@RequestBody ShiftLogin shiftLogin)
    {
        return shiftLogingService.saveShift(shiftLogin);
    }

    @PostMapping("closeShift")
    ResponseEntity<BaseResult> closeShift(@RequestBody ShiftLogin shiftLogin)
    {
        System.out.println(shiftLogin.getPremiumIds());
        return shiftLogingService.closeShift(shiftLogin);
    }

    @PostMapping("updateShift")
    ResponseEntity<BaseResult> updateShift(@RequestBody ShiftLogin shiftLogin)
    {
        System.out.println(shiftLogin);
        return shiftLogingService.updateShift(shiftLogin);
    }

    @PostMapping("consolidate-amounts")
    ResponseEntity<BaseResult> consolidateAmounts(@RequestBody ShiftLogin shiftLogin)
    {
        return shiftLogingService.consolidate(shiftLogin);
    }

    @PostMapping("updateShiftStatus")
    ResponseEntity<BaseResult> updateShiftStatus(@RequestBody ShiftLogin shiftLogin)
    {
        return shiftLogingService.updateShiftStatus(shiftLogin);
    }

    @GetMapping("getAllShiftByAgentId")
    ResponseEntity<BaseResult> getAllShiftByAgentId (@RequestParam Long agentId)
    {
        return shiftLogingService.getAllShiftByAgentId(agentId);
    }


    @GetMapping("getPendingShiftByAgentNumber")
    ResponseEntity<BaseResult> getPendingShiftByAgentNumber (@RequestParam String agentNumber)
    {
        return shiftLogingService.getPendingShiftByAgentNumber(agentNumber);
    }

    @GetMapping("getShiftById")
    ResponseEntity<BaseResult> getShiftById (@RequestParam String id)
    {
        return shiftLogingService.getShiftById(Long.parseLong(id));
    }

    @GetMapping("getShiftesByFilter")
    ResponseEntity<BaseResult> getShiftesByFilter(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("agentNumber") String agentNumber,
            @RequestParam("username") String username
    ) throws ParseException {
        // Add logging to check the values of the parameters
        System.out.println("Received startDate: " + startDate);
        System.out.println("Received endDate: " + endDate);
        System.out.println("Received agentNumber: " + agentNumber);
        System.out.println("Received username: " + username);

        return shiftLogingService.getShiftesByFilter(startDate, endDate, agentNumber, username);
    }


    @PostMapping("updateApproveFinance")
    ResponseEntity<BaseResult> updateApproveFinance(@RequestBody ShiftLogin shiftLogin)
    {
        return shiftLogingService.updateApproveFinance(shiftLogin);
    }


}

