package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftLogin extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long agentId;
    private Long subAreaOfficeId;
    private Date startDateTime;
    private String startDate;
    private String startTime;
    private Date endDateTime;
    private String agentFullnames;
    private String agentNumber;
    private String areaOfficeName;
    private String subAreaOfficeName;
    private String locationName;
    private String username;
    private String sbu;
    private String tenderedName;
    private String errorMsg;
    private String cashUpStatus;
    private String tenderUserName;

    private BigDecimal totalPaymentCashUSD;
    private BigDecimal totalPaymentCashZIG;
    private BigDecimal totalPaymentEcocashUSD;
    private BigDecimal totalPaymentEcocashZIG;
    private BigDecimal totalBankedAmountUSD;
    private BigDecimal totalPaymentEcocashUSDFuneral;
    private BigDecimal totalPaymentCashUSDFuneral;
    private String bankName;
    private String accountNumber;
    private String transactionRef;
    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<Long> premiumIds;
    private Date systemRecordedBankDate;
    private Date bankedDate;
    private Date financeApprovedDate;
    private String financeStatus;
    private String financeApproverName;
    private String financeApproverUsername;
    private String deviation;
    private String remarks;

    private BigDecimal totalConsolidatedUSD;
    private BigDecimal totalPaymentCashBeforeConsolidationUSD;
    private String consolidationStatus;
    private String consolidatedByName;
    private String consolidatedByUsername;

}



