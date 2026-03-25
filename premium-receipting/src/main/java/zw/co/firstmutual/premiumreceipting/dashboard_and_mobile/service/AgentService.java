package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import org.springframework.http.ResponseEntity;

public interface AgentService {

ResponseEntity<BaseResult> getAgentByAgentNumber(String agentNumber);

ResponseEntity<BaseResult> saveAgent(AgentRequest agentRequest);

ResponseEntity<BaseResult> findAgentById(Long hireId);


    ResponseEntity<BaseResult> getAllAgents();
}

