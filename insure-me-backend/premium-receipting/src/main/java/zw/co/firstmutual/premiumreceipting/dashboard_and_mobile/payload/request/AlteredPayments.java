package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AlteredPayments {
    private String selectedPremiumStatus;
    private String selectedCurrencyStatus;
    private BigDecimal newPremium;
    private BigDecimal oldPremium;
    private String newCurrency;
    private String oldCurrency;
    private String financeApproverUsername;
    private Long alterationId;
    private Long premiumId;
    private String firstName;
    private String lastName;
}
