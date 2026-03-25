package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.AreaOffice;
import com.vozhe.jwt.dashboard_and_mobile.models.SubAreaOffice;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.repository.AreaOfficeRepository;
import com.vozhe.jwt.dashboard_and_mobile.repository.SubAreaOfficeRepository;
import com.vozhe.jwt.dashboard_and_mobile.service.AreaOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AreaOfficeServiceImpl implements AreaOfficeService {

    private final AreaOfficeRepository areaOfficeRepository;
    private final SubAreaOfficeRepository subAreaOfficeRepository;


    @Override
    public ResponseEntity<BaseResult> getAllAreaOffice() {
        return ResponseEntity.ok(new BaseResult(areaOfficeRepository.findAll(), "Area Offices fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllSubAreaOffice() {
        return ResponseEntity.ok(new BaseResult(subAreaOfficeRepository.findAll(), "Sub Area Offices fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveAreaOffice(AreaOffice areaOffice) {
        areaOffice.setCreationDate(new Date());
        return ResponseEntity.ok(new BaseResult(areaOfficeRepository.save(areaOffice), "Area Offices Saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveSubAreaOffice(SubAreaOffice subAreaOffice) {
        subAreaOffice.setCreationDate(new Date());
        return ResponseEntity.ok(new BaseResult(subAreaOfficeRepository.save(subAreaOffice), "Area Offices saved successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllSubAreaOfficeByAreaOfficeId(Long areaOfficeId) {
        return ResponseEntity.ok(new BaseResult(subAreaOfficeRepository.findAllByAreaOfficeId(areaOfficeId), "Sub Area Offices fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAreaOfficeById(Long id) {
        return ResponseEntity.ok(new BaseResult(areaOfficeRepository.findById(id), "Area Office fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getSubAreaOfficeById(Long id) {
        return ResponseEntity.ok(new BaseResult(subAreaOfficeRepository.findById(id), "Sub Area Offices fetched successfully","00",200));
    }

    @Override
    public AreaOffice findAreaOfficeById(Long id) {
        return areaOfficeRepository.findById(id).get();
    }

    @Override
    public SubAreaOffice findSubAreaOfficeById(Long id) {
        return subAreaOfficeRepository.findById(id).get();
    }
}

