package com.vozhe.jwt.dashboard_and_mobile.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDash {
    private Long id;
    private String agentNumber;
    private String policyNumber;
    private String payablePhoneNumber;
    private BigDecimal payableAmount;
    private String currency;
    private  String paymentOption;
    private String sbu;
    private String transactionId;
    private String subAreaOfficeName;
    private String areaOfficeName;
    private String policyHolderFirstName;
    private String policyHolderLastName;
    private String policyHolderContractPremium;
    private String mobileNumber;
    private String policyHolderIDNumber;
    private String modifiedBy;
}