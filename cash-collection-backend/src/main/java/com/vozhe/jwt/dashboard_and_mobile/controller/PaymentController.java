package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.models.BankedAmountsForAlteration;
import com.vozhe.jwt.dashboard_and_mobile.models.FuneralOption;
import com.vozhe.jwt.dashboard_and_mobile.models.PremiumPaymentForAlteration;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AlteredPayments;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PaymentRequestDash;
import com.vozhe.jwt.dashboard_and_mobile.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("getAllPayments")
    ResponseEntity<BaseResult> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("getPaymentById")
    ResponseEntity<BaseResult> getPaymentById(@RequestParam("premiumId") Long premiumId) {
        return paymentService.getPaymentById(premiumId);
    }

    @GetMapping("getAllFuneralOptions")
    ResponseEntity<BaseResult> getAllFuneralOptions() {
        return paymentService.getAllFuneralOptions();
    }
    @GetMapping("getAllPaymentsByUsername")
    ResponseEntity<BaseResult> getAllPaymentsByUsername(@RequestParam("username") String username) {
        return paymentService.getAllPaymentsByUsername(username);
    }

    @GetMapping("getAllPaymentsByAgentId")
    ResponseEntity<BaseResult> getAllPaymentsByAgentId(@RequestParam("agentId") Long agentId) {
        return paymentService.getAllPaymentsByAgentId(agentId);
    }

    @GetMapping("getAllPaymentsByAgentNumber")
    ResponseEntity<BaseResult> getAllPaymentsByAgentNumber(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getAllPaymentsByAgentNumber(agentNumber);
    }

    @GetMapping("get-payments-by-policy")
    ResponseEntity<BaseResult> getPaymentsByPolicy(@RequestParam("policy") String policy) {
        return paymentService.getPaymentsByPolicy(policy);
    }


    @GetMapping("getAllRejectedPaymentsByAgentNumber")
    ResponseEntity<BaseResult> getAllRejectedPaymentsByAgentNumber(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getAllRejectedPaymentsByAgentNumber(agentNumber);
    }

    @GetMapping("getGrandTotalsByAgentNumber")
    ResponseEntity<BaseResult> getGrandTotalsByAgentNumber(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getGrandTotalsByAgentNumber(agentNumber);
    }

    @GetMapping("getRejectedGrandTotalsByAgentNumber")
    ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumber(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getRejectedGrandTotalsByAgentNumber(agentNumber);
    }

    @GetMapping("checkPendingAlteration")
    ResponseEntity<BaseResult> checkPendingAlteration(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.checkPendingAlteration(agentNumber);
    }

    @GetMapping("getGrandTotalsByAgentNumberAndSBU")
    ResponseEntity<BaseResult> getGrandTotalsByAgentNumberAndSBU(
            @RequestParam("agentNumber") String agentNumber,
            @RequestParam("sbu") String sbu) {
        return paymentService.getGrandTotalsByAgentNumberAndSBU(agentNumber, sbu);
    }

    @GetMapping("getRejectedGrandTotalsByAgentNumberAndSBU")
    ResponseEntity<BaseResult> getRejectedGrandTotalsByAgentNumberAndSBU(
            @RequestParam("agentNumber") String agentNumber,
            @RequestParam("sbu") String sbu) {
        return paymentService.getRejectedGrandTotalsByAgentNumberAndSBU(agentNumber, sbu);
    }

    @PostMapping("savePayment")
    ResponseEntity<BaseResult> savePayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.savePayment(paymentRequest);
    }
    @PostMapping("saveFuneralOptions")
    ResponseEntity<BaseResult> saveFuneralOptions(@RequestBody FuneralOption funeralOption) {
        return paymentService.saveFuneralOptions(funeralOption);
    }

    @PostMapping("editsavePayment")
    ResponseEntity<BaseResult> editsavePayment(@RequestBody PaymentRequestDash paymentRequest) {
        return paymentService.editsavePayment(paymentRequest);
    }

    @PostMapping("updateAlterarionByFinance")
    ResponseEntity<BaseResult> updateAlterarionByFinance(@RequestBody AlteredPayments alteredPayments) {
        return paymentService.updateAlterarionByFinance(alteredPayments);
    }


    @GetMapping("getPremiumsByFilter")
    ResponseEntity<BaseResult> getPremiumsByFilter(
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

        return paymentService.getPremiumsByFilter(startDate, endDate, agentNumber, username);
    }

    @GetMapping("getPremiumsRejectedByFilter")
    ResponseEntity<BaseResult> getPremiumsRejectedByFilter(
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

        return paymentService.getPremiumsRejectedByFilter(startDate, endDate, agentNumber, username);
    }

    @GetMapping("getPremiumsAccountByFilter")
    ResponseEntity<BaseResult> getPremiumsAccountByFilter(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("policyNumber") String policyNumber,
            @RequestParam("username") String username
    ) throws ParseException {
        // Add logging to check the values of the parameters
        System.out.println("Received startDate: " + startDate);
        System.out.println("Received endDate: " + endDate);
        System.out.println("Received policyNumber: " + policyNumber);
        System.out.println("Received username: " + username);

        return paymentService.getPremiumsAccountByFilter(startDate, endDate, policyNumber, username);
    }

    @PostMapping("post-premium-alteration")
    ResponseEntity<BaseResult> alterPremium(@RequestBody PremiumPaymentForAlteration premiumPaymentForAlteration) {
        return paymentService.alterPremium(premiumPaymentForAlteration);
    }

    @PostMapping("post-banked-amount-alteration")
    ResponseEntity<BaseResult> alterBankingDetails(@RequestBody BankedAmountsForAlteration bankedAmountsForAlteration) {
        return paymentService.alterBankingDetails(bankedAmountsForAlteration);
    }
    @GetMapping("getAllAlteredPremiumPaymentsByAgent")
    ResponseEntity<BaseResult> getAllAlteredPremiumPaymentsByAgent(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getAllAlteredPremiumPaymentsByAgent(agentNumber);
    }

    @GetMapping("getAllAlteredPremiumPayments")
    ResponseEntity<BaseResult> getAllAlteredPremiumPayments() {
        return paymentService.getAllAlteredPremiumPayments();
    }

    @GetMapping("getAllAlteredBankingDetails")
    ResponseEntity<BaseResult> getAllAlteredBankingDetails() {
        return paymentService.getAllAlteredBankingDetails();
    }


    @GetMapping("getAllAlteredBankedDetailsPaymentsByAgent")
    ResponseEntity<BaseResult> getAllAlteredBankedDetailsPaymentsByAgent(@RequestParam("agentNumber") String agentNumber) {
        return paymentService.getAllAlteredBankedDetailsPaymentsByAgent(agentNumber);
    }

    @GetMapping("consistent-policy-holders-last-four-months")
    public ResponseEntity<BaseResult> getConsistentPolicyHoldersLastFourMonths() {
        BaseResult result = paymentService.findConsistentPolicyHoldersInLastFourMonths();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }

    @GetMapping("consistent-policy-holders-last-six-months")
    public ResponseEntity<BaseResult> getConsistentPolicyHoldersLastSixMonths( @RequestParam("agentNumber") String place) {
        BaseResult result = paymentService.getConsistentPolicyHoldersLastSixMonths(place);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
    }


    /**
     * Handles the file upload and initiates the batch update.
     * * METHOD: POST
     * URL: /api/premium-payments/batch-update
     * REQUEST: multipart/form-data (with a parameter named 'file')
     */
    @PostMapping("batch-update")
    public ResponseEntity<String> updatePoliciesFromFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload. ❌");
        }

        // Check for common CSV or Excel MIME types
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.contains("csv") && !contentType.contains("excel") && !contentType.contains("spreadsheet"))) {
            return ResponseEntity.badRequest().body("Invalid file type. Only CSV or Excel files are supported. ❌");
        }

        try {
            // Delegate the business logic to the service layer
            String resultSummary = paymentService.updatePolicyNumbersFromFile(file);
            return ResponseEntity.ok(resultSummary);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to read the file due to an IO error. ⚠️ Error: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred during the update process. ⚠️ Error: " + e.getMessage());
        }
    }

}