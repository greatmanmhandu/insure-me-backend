package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import org.springframework.http.ResponseEntity;

public interface AgentService {

ResponseEntity<BaseResult> getAgentByAgentNumber(String agentNumber);

ResponseEntity<BaseResult> saveAgent(AgentRequest agentRequest);

ResponseEntity<BaseResult> findAgentById(Long hireId);


    ResponseEntity<BaseResult> getAllAgents();
}
