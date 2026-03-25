package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AgentRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.AreaOfficerRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.AgentResponse;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.AreaOfficerResponse;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.*;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AreaOfficeService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AreaOfficerService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.DeviceService;
import zw.co.firstmutual.premiumreceipting.enums.Status;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AreaOfficerServiceImpl implements AreaOfficerService {
    private final AreaOfficerRepository areaOfficerRepository;
    private final UserServiceImpl userService;

    @Override
    public ResponseEntity<BaseResult> getAreaOfficerByAreaOfficerNumber(String areaOfficerNumber) {
        AreaOfficer areaOfficer = areaOfficerRepository.findByAreaOfficerNumber(areaOfficerNumber);
        AreaOfficerResponse areaOfficerResponse = new AreaOfficerResponse();
        areaOfficerResponse.setAreaOfficer(areaOfficer);
        return ResponseEntity.ok(new BaseResult(areaOfficerResponse, "AreaOfficer fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveAreaOfficer(AreaOfficerRequest areaOfficerRequest) {

        AreaOfficer areaOfficer = new AreaOfficer();
        areaOfficer.setFirstName(areaOfficerRequest.getFirstName());
        areaOfficer.setLastName(areaOfficerRequest.getLastName());
        areaOfficer.setId(areaOfficerRequest.getId());
        areaOfficer.setSubAreaOfficeId(areaOfficerRequest.getSubAreaOfficeId());
        areaOfficer.setAreaOfficeId(areaOfficerRequest.getAreaOfficeId());
        areaOfficer.setAddress(areaOfficerRequest.getAddress());
        areaOfficer.setGender(areaOfficerRequest.getGender());
        areaOfficer.setAreaOfficerNumber(areaOfficerRequest.getAreaOfficerNumber());
        areaOfficer.setDob(areaOfficerRequest.getDob());
        areaOfficer.setPhoneNumber(areaOfficerRequest.getPhoneNumber());
        areaOfficer.setSbu(areaOfficerRequest.getSbu());
        areaOfficer.setNationalId(areaOfficerRequest.getNationalId());
        areaOfficer.setPassword(userService.encryptPassword(areaOfficerRequest.getPassword()));
        areaOfficer.setActiveStatus(true);
        areaOfficer.setCreationDate(new Date());
        areaOfficerRepository.save(areaOfficer);
        User user = new User();
        user.setAreaOfficerNumber(areaOfficerRequest.getAreaOfficerNumber());
        user.setFirstName(areaOfficerRequest.getFirstName());
        user.setLastName(areaOfficerRequest.getLastName());
        user.setUsername(areaOfficerRequest.getAreaOfficerNumber());
        user.setPassword(areaOfficerRequest.getPassword());
        user.setCreationDate(new Date());
        user.setRoles("Area Officer");
        user.setSbu(areaOfficerRequest.getSbu());
        userService.createAgent(user);
        return ResponseEntity.ok(new BaseResult(null,"Area Officer saved successfully","00",200));
    }


    public AreaOfficer findById(Long id){
        AreaOfficer areaOfficer;
        Optional<AreaOfficer> optionalAgent = areaOfficerRepository.findById(id);
        if (optionalAgent.isPresent()) {
            areaOfficer = optionalAgent.get();
        } else {
            throw new EntityNotFoundException("AreaOfficer with ID " + id + " not found");
        }
        return areaOfficer;
    }

    @Override
    public ResponseEntity<BaseResult> findAreaOfficerById(Long id) {
        Optional<AreaOfficer> areaOfficer = Optional.ofNullable(findById(id));
        return ResponseEntity.ok(new BaseResult(areaOfficer, "AreaOfficer fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAreaOfficers() {
        return ResponseEntity.ok(new BaseResult(areaOfficerRepository.findAll(), "AreaOfficer fetched successfully","00",200));

    }
}

