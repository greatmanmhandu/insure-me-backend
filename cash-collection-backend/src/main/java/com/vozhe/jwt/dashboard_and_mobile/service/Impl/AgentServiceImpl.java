package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.*;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.AgentRequest;
import com.vozhe.jwt.dashboard_and_mobile.payload.response.AgentResponse;
import com.vozhe.jwt.dashboard_and_mobile.repository.*;
import com.vozhe.jwt.dashboard_and_mobile.service.AreaOfficeService;
import com.vozhe.jwt.dashboard_and_mobile.service.DeviceService;
import com.vozhe.jwt.enums.Status;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final UserServiceImpl userService;
    private final DeviceService deviceService;
    private final AreaOfficeService areaOfficeService;

    @Override
    public ResponseEntity<BaseResult> getAgentByAgentNumber(String agentNumber) {
        Agent agent = agentRepository.findByAgentNumber(agentNumber);
        Printer printer = deviceService.findPrinterById(agent.getPrinterId());
        MobileDevice mobileDevice = deviceService.findMobileDeviceById(agent.getMobileDeviceId());
        AreaOffice areaOffice = areaOfficeService.findAreaOfficeById(agent.getAreaOfficeId());
//        SubAreaOffice subAreaOffice = areaOfficeService.findSubAreaOfficeById(agent.getSubAreaOfficeId());
        AgentResponse agentResponse = new AgentResponse();
        agentResponse.setAgent(agent);
        agentResponse.setAreaOffice(areaOffice);
        agentResponse.setMobileDevice(mobileDevice);
//        agentResponse.setSubAreaOffice(subAreaOffice);
        agentResponse.setPrinter(printer);
        return ResponseEntity.ok(new BaseResult(agentResponse, "Agent fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> saveAgent(AgentRequest agentRequest) {

        Agent agent = new Agent();
        agent.setFirstName(agentRequest.getFirstName());
        agent.setLastName(agentRequest.getLastName());
        agent.setId(agentRequest.getId());
        agent.setMobileDeviceId(agentRequest.getMobileDeviceId());
        agent.setSubAreaOfficeId(agentRequest.getSubAreaOfficeId());
        agent.setAreaOfficeId(agentRequest.getAreaOfficeId());
        agent.setPrinterId(agentRequest.getPrinterId());
        agent.setAddress(agentRequest.getAddress());
        agent.setGender(agentRequest.getGender());
        agent.setAgentNumber(agentRequest.getAgentNumber());
        agent.setDob(agentRequest.getDob());
        agent.setPhoneNumber(agentRequest.getPhoneNumber());
        agent.setSbu(agentRequest.getSbu());
        agent.setNationalId(agentRequest.getNationalId());
        agent.setPassword(userService.encryptPassword(agentRequest.getPassword()));
        agent.setActiveStatus(true);
        agent.setCreationDate(new Date());
        agentRepository.save(agent);
        User user = new User();
        user.setAgentNumber(agentRequest.getAgentNumber());
        user.setFirstName(agentRequest.getFirstName());
        user.setLastName(agentRequest.getLastName());
        user.setUsername(agentRequest.getAgentNumber());
        user.setPassword(agentRequest.getPassword());
        user.setCreationDate(new Date());
        user.setRoles("Agent");
        user.setSbu(agentRequest.getSbu());
        userService.createAgent(user);
        return ResponseEntity.ok(new BaseResult(null,"Agent saved successfully","00",200));
    }


    public Agent findById(Long id){
        Agent agent;
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        if (optionalAgent.isPresent()) {
             agent = optionalAgent.get();
        } else {
            throw new EntityNotFoundException("Agent with ID " + id + " not found");
        }
        return agent;
    }

    @Override
    public ResponseEntity<BaseResult> findAgentById(Long id) {
        Optional<Agent> agent = Optional.ofNullable(findById(id));
        return ResponseEntity.ok(new BaseResult(agent, "Agent fetched successfully","00",200));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAgents() {
        return ResponseEntity.ok(new BaseResult(agentRepository.findAll(), "Agents fetched successfully","00",200));

    }
}
