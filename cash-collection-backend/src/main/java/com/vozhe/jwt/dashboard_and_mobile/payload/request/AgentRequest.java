package com.vozhe.jwt.dashboard_and_mobile.payload.request;

import com.vozhe.jwt.enums.SBU;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgentRequest {
    private Long id;
    private Long printerId;
    private Long mobileDeviceId;
    private Long areaOfficeId;
    private Long subAreaOfficeId;
    private String gender;
    private String firstName;
    private String lastName;
    private String password;
    private String nationalId;
    private String agentNumber;
    private String phoneNumber;
    private String sbu;
    private String dob;
    private String address;
}