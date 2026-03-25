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
    public class PaymentTransactionDTO {
        private Long transactionId; // Using PremiumPayment's ID as transactionId for simplicity
        private BigDecimal payableAmount;
        private Date paymentDate;
//        private Date creationDate; // From GenericAuditTrail
        private String transactionStatus; // From PaymentStatus enum
//        private String channel;
        private String contact;
    }


