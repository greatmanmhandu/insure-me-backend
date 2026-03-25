package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import zw.co.firstmutual.premiumreceipting.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PremiumPayment extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)

    private Long id;
    private Long agentId;
    private Long policyHolderId;
    private BigDecimal payableAmount;
    private String msisdn;
    private String username;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String payableMobileNumber;
    @Column(name = "policy_number")
    private String policyNumber;
    private Date paymentDate;
//    @Enumerated(EnumType.STRING)
//    private Channel channel;
    private String currency;
    private String sbu;
    @Enumerated(EnumType.STRING)
    private Status status;
    private  String paymentOption;
    private String agentNumber;
    private String transactionId;
    private String subAreaOfficeName;
    private String areaOfficeName;
    private BigDecimal totalSum;
    private String policyHolderFirstName;
    private String policyHolderLastName;
    private String policyHolderContractPremium;
    private String commitStatus;
    private String mobileNumber;
    private String policyHolderIDNumber;
    private String alterationStatus;
}




