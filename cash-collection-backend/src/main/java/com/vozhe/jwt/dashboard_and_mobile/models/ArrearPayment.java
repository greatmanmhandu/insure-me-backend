package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.Channel;
import com.vozhe.jwt.enums.SBU;
import com.vozhe.jwt.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrearPayment extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)

    private Long id;
    private Long agentId;
    private Long policyHolderId;
    private BigDecimal payableAmount;
    private LocalDateTime paymentDate;
    @Enumerated(EnumType.STRING)
    private Channel channel;
    @Enumerated(EnumType.STRING)
    private SBU sbu;
    @Enumerated(EnumType.STRING)
    private Status status;
}



