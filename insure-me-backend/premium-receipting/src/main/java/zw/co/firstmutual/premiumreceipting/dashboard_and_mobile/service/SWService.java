package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SWUpdates;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import org.springframework.http.ResponseEntity;

public interface SWService {

    ResponseEntity<BaseResult> saveUpdates(SWUpdates swUpdates);
    ResponseEntity<BaseResult> getUpdates();
}

