package com.vozhe.jwt.dashboard_and_mobile.controller;

import com.vozhe.jwt.dashboard_and_mobile.models.Help;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import com.vozhe.jwt.dashboard_and_mobile.service.AgentService;
import com.vozhe.jwt.dashboard_and_mobile.service.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelpController {

    private final HelpService helpService;

    @GetMapping("get-help-list")
    ResponseEntity<BaseResult> getHelpList ()
    {
        return helpService.getAllHelpList();
    }

    @PostMapping("help-message")
    ResponseEntity<BaseResult> saveHelp(@RequestBody Help help)
    {
        return helpService.saveHelp(help);
    }

}
