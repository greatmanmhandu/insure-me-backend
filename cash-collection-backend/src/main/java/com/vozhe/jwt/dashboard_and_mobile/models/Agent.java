package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
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
    private String username;
    private String sbu;
    private String dob;
    private String address;
    private boolean activeStatus;
}



