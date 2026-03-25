package com.vozhe.jwt.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaOfficer extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long areaOfficeId;
    private Long subAreaOfficeId;
    private String gender;
    private String firstName;
    private String lastName;
    private String password;
    private String nationalId;
    private String areaOfficerNumber;
    private String phoneNumber;
    private String username;
    private String sbu;
    private String dob;
    private String address;
    private boolean activeStatus;
}



