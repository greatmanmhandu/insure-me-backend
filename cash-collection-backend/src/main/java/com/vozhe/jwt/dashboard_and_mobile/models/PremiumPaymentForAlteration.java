package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.Channel;
import com.vozhe.jwt.enums.PaymentStatus;
import com.vozhe.jwt.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PremiumPaymentForAlteration extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)

    private Long id;
    private Long premiumId;
    private BigDecimal payableAmount;
    private String remarks;
    private String currency;
    private String policyHolderFirstName;
    private String policyHolderLastName;
    private String status;
    private Date alterationDate;
    private String agentNumber;
    private String type;
    private String sbu;
}



