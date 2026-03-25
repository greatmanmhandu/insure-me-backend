package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.ShiftLogin;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface ShiftLogingService {


    ResponseEntity<BaseResult> getAllShifts();

    ResponseEntity<BaseResult> getAllShiftsByUsername(String username);

    ResponseEntity<BaseResult> saveShift(ShiftLogin shiftLogin);

    ResponseEntity<BaseResult> getAllShiftByAgentId(Long agentId);

    ResponseEntity<BaseResult> closeShift(ShiftLogin shiftLogin);

    ResponseEntity<BaseResult> getPendingShiftByAgentNumber(String agentNumber);

    public ShiftLogin getPendingShiftByAgentNumberSecond(String agentNumber);

    ResponseEntity<BaseResult> getAllShiftsByTenderUsername(String tenderUserName);

    ResponseEntity<BaseResult> updateShift(ShiftLogin shiftLogin);
    ResponseEntity<BaseResult> updateShiftStatus(ShiftLogin shiftLogin);

    ResponseEntity<BaseResult> getShiftesByFilter(String startDate, String endDate, String agentNumber, String username) throws ParseException;

    ResponseEntity<BaseResult> updateApproveFinance(ShiftLogin shiftLogin);

    ResponseEntity<BaseResult> getAllShiftsByTender(String tenderUserName);

    ResponseEntity<BaseResult> consolidate(ShiftLogin shiftLogin);

    ResponseEntity<BaseResult> getShiftById(long id);

    ResponseEntity<BaseResult> getAllShiftByParams(String cashUpStatus, String consolidationStatus, String sbu);

    ResponseEntity<BaseResult> getAllShiftsPending();

    ResponseEntity<BaseResult> getAllShiftsWithMissingBankedDetails();

    ResponseEntity<BaseResult> getFinanceBankingDetails();
}
