package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import com.vozhe.jwt.dashboard_and_mobile.service.AgentService;
import com.vozhe.jwt.dashboard_and_mobile.service.Impl.ExcelDataService;
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
public class AgentController {

    private final AgentService agentService;
    private final ExcelDataService excelDataService;

    @GetMapping("getAllAgents")
    ResponseEntity<BaseResult> getAllAgents ()
    {
        return agentService.getAllAgents();
    }
    @GetMapping("getAgentByAgentNumber")
    ResponseEntity<BaseResult> getAgentByUsername(@RequestParam("agentNumber") String agentNumber) {
        return agentService.getAgentByAgentNumber(agentNumber);
    }

    @PostMapping("saveAgent")
    ResponseEntity<BaseResult> saveAgent(@RequestBody AgentRequest agentRequest)
    {
        return agentService.saveAgent(agentRequest);
    }

    @PostMapping("upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        System.out.println("file");
        try {
            System.out.println(file);
            excelDataService.saveExcelData(file);
            return ResponseEntity.ok("Excel data saved successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the Excel file.");
        }
    }


    @GetMapping("getAgentByHireId")
    ResponseEntity<BaseResult> getAgentByHireId (@RequestParam Long id)
    {
        return agentService.findAgentById(id);
    }

}
