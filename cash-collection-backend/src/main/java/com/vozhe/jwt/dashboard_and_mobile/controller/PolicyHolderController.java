package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.PolicyHolderRequest;
import com.vozhe.jwt.dashboard_and_mobile.service.Impl.ExcelService;
import com.vozhe.jwt.dashboard_and_mobile.service.PolicyHolderService;
import com.vozhe.jwt.rest_template.ApiService;
import com.vozhe.jwt.rest_template.MemberData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PolicyHolderController {

    private final PolicyHolderService policyHolderService;

    private final ExcelService excelService;


    @Autowired
    private ApiService apiService;

//    @GetMapping("member/{memberId}")
//    public MemberData getMember(@PathVariable String memberId) {
//        return apiService.getMemberData(memberId);
//    }

    @GetMapping("member/{memberId}")
    public Object getMember(@PathVariable String memberId) {
        return apiService.getMemberDataJson(memberId);
    }

    @GetMapping("msu/{regNumber}")
    public Object getStudent(@PathVariable String regNumber) {
        return apiService.getStudent(regNumber);
    }
    @GetMapping("member-balance/{memberId}")
    public Object getMemberBalance(@PathVariable String memberId) {
        return apiService.getMemberBalanceDataJson(memberId);
    }


    @GetMapping("getAllPolicyHolders")
    ResponseEntity<BaseResult> getAllPolicyHolders ()
    {
        return policyHolderService.getAllPolicyHolders();
    }


    @GetMapping("findPolicyHolderByPolicyNumber/{policyNumber}")
    ResponseEntity<BaseResult> findPolicyHolderByPolicyNumber(@PathVariable("policyNumber") String policyNumber) {
        return policyHolderService.findPolicyHolderByPolicyNumber(policyNumber);
    }

    @GetMapping("findPolicyHolderByNationalIDorPhone/{searchID}/{searchPhone}")
    ResponseEntity<BaseResult> findPolicyHolderByNationalIDorPhone(@PathVariable("searchID") String searchID,@PathVariable("searchPhone") String searchPhone) {
        return policyHolderService.findPolicyHolderByNationalIDorPhone(searchID,searchPhone);
    }

    @GetMapping("findListOfPolicyHolderByNationalIDorPhone/{searchID}/{searchPhone}")
    ResponseEntity<BaseResult> findListOfPolicyHolderByNationalIDorPhone(@PathVariable("searchID") String searchID,@PathVariable("searchPhone") String searchPhone) {
        return policyHolderService.findListOfPolicyHolderByNationalIDorPhone(searchID,searchPhone);
    }

    @PostMapping("savePolicyHolders")
    ResponseEntity<BaseResult> savePolicyHolders(@RequestBody PolicyHolderRequest policyHolderRequest)
    {
        System.out.println(policyHolderRequest);
        return policyHolderService.savePolicyHolders(policyHolderRequest);
    }

    @GetMapping("getPolicyHolderById")
    ResponseEntity<BaseResult> getPolicyHolderById (@RequestParam Long id)
    {
        return policyHolderService.getPolicyHolderById(id);
    }

//    @GetMapping("temp-save-inbatches")
//    ResponseEntity<BaseResult> saveInBatchs ()
//    {
//        excelService.processCSVFile();
//        return null;
//    }

    @PostMapping("/upload-file")
    public ResponseEntity<BaseResult> uploadFile(@RequestParam("file") MultipartFile file) {
        excelService.processUploadedFile(file);
        return ResponseEntity.ok(new BaseResult("File uploaded. Processing batches..."));
    }

    @GetMapping("getPolicyHoldersByFilter")
    ResponseEntity<BaseResult> getPolicyHoldersByFilter(
            @RequestParam("policyHolders") String policyHolders
    ) throws ParseException {
        System.out.println("Received policyHolders: " + policyHolders);

        return policyHolderService.getPolicyHoldersByFilter(policyHolders);
    }


    @PostMapping("updatePolicyHolders")
    ResponseEntity<BaseResult> updatePolicyHolders(@RequestBody PolicyHolderRequest policyHolderRequest)
    {
        System.out.println(policyHolderRequest);
        return policyHolderService.updatePolicyHolders(policyHolderRequest);
    }

    @PostMapping("deletePolicyHolders")
    ResponseEntity<BaseResult> deletePolicyHolders(@RequestBody PolicyHolderRequest policyHolderRequest)
    {

        return policyHolderService.deletePolicyHolders(policyHolderRequest.getId());
    }

}
