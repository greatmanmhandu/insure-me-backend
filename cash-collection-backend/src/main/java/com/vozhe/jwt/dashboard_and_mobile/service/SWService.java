package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.SWUpdates;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import org.springframework.http.ResponseEntity;

public interface SWService {

    ResponseEntity<BaseResult> saveUpdates(SWUpdates swUpdates);
    ResponseEntity<BaseResult> getUpdates();
}
