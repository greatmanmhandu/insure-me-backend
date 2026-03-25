package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrantPaymentResponse {

    private String agentNumber;
    private Long policyHolderId;
    private BigDecimal totalPaymentCashUSD;
    private BigDecimal totalPaymentCashZIG;
    private BigDecimal totalPaymentEcocashUSD;
    private BigDecimal totalPaymentEcocashZIG;
    private BigDecimal totalPaymentCashUSDFuneral;
    private BigDecimal totalPaymentEcocashUSDFuneral;
    private String paymentDate;
    private SBU sbu;
    private List<Long> primiumIds;
}




