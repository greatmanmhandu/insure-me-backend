package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.*;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.response.AgentResponse;
import com.vozhe.jwt.dashboard_and_mobile.repository.AgentRepository;
import com.vozhe.jwt.dashboard_and_mobile.repository.SWRepository;
import com.vozhe.jwt.dashboard_and_mobile.service.AgentService;
import com.vozhe.jwt.dashboard_and_mobile.service.AreaOfficeService;
import com.vozhe.jwt.dashboard_and_mobile.service.DeviceService;
import com.vozhe.jwt.dashboard_and_mobile.service.SWService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SWServiceImpl implements SWService {
    private final SWRepository swRepository;


    @Override
    public ResponseEntity<BaseResult> saveUpdates(SWUpdates swUpdates) {
        return ResponseEntity.ok(new BaseResult(swRepository.save(swUpdates),"Update saved successfully","00",200));
    }
    @Override
    public ResponseEntity<BaseResult> getUpdates() {
        return ResponseEntity.ok(new BaseResult(swRepository.findAll(), "Updates fetched successfully","00",200));

    }
}
