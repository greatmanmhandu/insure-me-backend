package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.Help;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import org.springframework.http.ResponseEntity;

public interface HelpService {
    ResponseEntity<BaseResult> saveHelp(Help help);

    ResponseEntity<BaseResult> getAllHelpList();
}
