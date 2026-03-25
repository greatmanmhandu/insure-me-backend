package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AreaOfficerRequest;
import org.springframework.http.ResponseEntity;

public interface AreaOfficerService {

ResponseEntity<BaseResult> getAreaOfficerByAreaOfficerNumber(String agentNumber);

ResponseEntity<BaseResult> saveAreaOfficer(AreaOfficerRequest areaOfficerRequest);

ResponseEntity<BaseResult> findAreaOfficerById(Long hireId);


    ResponseEntity<BaseResult> getAllAreaOfficers();
}

