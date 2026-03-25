package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.AreaOffice;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SubAreaOffice;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import org.springframework.http.ResponseEntity;

public interface AreaOfficeService {


    ResponseEntity<BaseResult> getAllAreaOffice();

    ResponseEntity<BaseResult> getAllSubAreaOffice();

    ResponseEntity<BaseResult> saveAreaOffice(AreaOffice areaOffice);

    ResponseEntity<BaseResult> saveSubAreaOffice(SubAreaOffice subAreaOffice);

    ResponseEntity<BaseResult> getAllSubAreaOfficeByAreaOfficeId(Long areaOfficeId);

    ResponseEntity<BaseResult> getAreaOfficeById(Long id);

    ResponseEntity<BaseResult> getSubAreaOfficeById(Long id);

    AreaOffice findAreaOfficeById(Long id);

    SubAreaOffice findSubAreaOfficeById(Long id);
}

