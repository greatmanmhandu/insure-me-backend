package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AreaOfficerRequest;
import org.springframework.http.ResponseEntity;

public interface AreaOfficerService {

ResponseEntity<BaseResult> getAreaOfficerByAreaOfficerNumber(String agentNumber);

ResponseEntity<BaseResult> saveAreaOfficer(AreaOfficerRequest areaOfficerRequest);

ResponseEntity<BaseResult> findAreaOfficerById(Long hireId);


    ResponseEntity<BaseResult> getAllAreaOfficers();
}
