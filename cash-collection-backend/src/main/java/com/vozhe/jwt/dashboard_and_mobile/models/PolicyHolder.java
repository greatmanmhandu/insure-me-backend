package com.vozhe.jwt.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "policy_holder_life")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolder extends GenericAuditTrail<String> {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String policyNumber;
    private String idNumber;
    private String address;
    private String dob;
    private String agentName;
    private String agentNumber;
    private String cover;
    private String contractPremium;
    private String productType;
    private String sumAssured;
    private String product;
}
