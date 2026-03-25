package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.Help;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import org.springframework.http.ResponseEntity;

public interface HelpService {
    ResponseEntity<BaseResult> saveHelp(Help help);

    ResponseEntity<BaseResult> getAllHelpList();
}

