package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.AreaOffice;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.SubAreaOffice;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AreaOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AreaOficeController {

    private final AreaOfficeService areaOfficeService;

    @GetMapping("getAllAreaOffice")
    ResponseEntity<BaseResult> getAllAreaOffice ()
    {
        return areaOfficeService.getAllAreaOffice();
    }
    @GetMapping("getAllSubAreaOffice")
    ResponseEntity<BaseResult> getAllSubAreaOffice() {
        return areaOfficeService.getAllSubAreaOffice();
    }

    @PostMapping("saveAreaOffice")
    ResponseEntity<BaseResult> saveAreaOffice(@RequestBody AreaOffice areaOffice)
    {
        return areaOfficeService.saveAreaOffice(areaOffice);
    }

    @PostMapping("saveSubAreaOffice")
    ResponseEntity<BaseResult> saveSubAreaOffice(@RequestBody SubAreaOffice subAreaOffice)
    {
        return areaOfficeService.saveSubAreaOffice(subAreaOffice);
    }

    @GetMapping("getAreaOfficeById")
    ResponseEntity<BaseResult> getAreaOfficeById (@RequestParam Long id)
    {
        return areaOfficeService.getAreaOfficeById(id);
    }

    @GetMapping("getSubAreaOfficeById")
    ResponseEntity<BaseResult> getSubAreaOfficeById (@RequestParam Long id)
    {
        return areaOfficeService.getSubAreaOfficeById(id);
    }
    @GetMapping("getAllSubAreaOfficeByAreaOfficeId")
    ResponseEntity<BaseResult> getAllSubAreaOfficeByAreaOfficeId (@RequestParam Long areaOfficeId)
    {
        return areaOfficeService.getAllSubAreaOfficeByAreaOfficeId(areaOfficeId);
    }

}

