package com.vozhe.jwt.dashboard_and_mobile.payload.response;

import com.vozhe.jwt.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponse {

    private Long id;
    private Long agentId;
    private Long policyHolderId;
    private BigDecimal payableAmount;
    private LocalDateTime paymentDate;
    private String channel;
    private String sbu;
    private String status;
}



