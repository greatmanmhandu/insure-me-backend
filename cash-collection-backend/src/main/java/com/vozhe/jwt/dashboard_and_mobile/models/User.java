package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends GenericAuditTrail<String> {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String idNumber;
    private String address;
    private Date DOB;
    private String password;
    @Column(unique=true)
    private String username;
    private String roles;
    private String sbu;
    private String agentNumber;
    private String areaOfficerNumber;
    private String icon;
}
