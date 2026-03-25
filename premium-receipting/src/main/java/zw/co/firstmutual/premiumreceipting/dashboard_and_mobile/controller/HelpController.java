package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Help;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AgentService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.HelpService;
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

