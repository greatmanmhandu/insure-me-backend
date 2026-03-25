package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolderPaymentDetailsDTO {
//    private Long policyHolderId;
    private String policyHolderFirstName;
    private String policyHolderLastName;
    private String policyHolderIDNumber;
    private String policyNumber;
    private String mobileNumber;
    private String policyHolderContractPremium; // Assuming this is relevant detail
    private String contact;

    private List<PaymentTransactionDTO> transactions;

}

