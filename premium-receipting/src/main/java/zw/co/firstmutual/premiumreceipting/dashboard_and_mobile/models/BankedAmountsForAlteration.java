package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankedAmountsForAlteration extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)

    private Long id;
    private Long shiftId;
    private BigDecimal bankedAmt;
    private String deviation;
    private String remarks;
    private String accountNumber;
    private String bankName;
    private String transactionRef;
    private String totalPaymentCashUSD;
    private String status;
    private Date alterationDate;
    private String agentNumber;
    private String type;
    private String sbu;
}




