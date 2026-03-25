package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
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
}
