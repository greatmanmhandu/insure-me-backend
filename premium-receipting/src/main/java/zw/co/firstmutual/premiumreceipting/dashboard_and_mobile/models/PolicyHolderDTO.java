package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolderDTO {
    private String phoneNumber;
    private String idNumber;
    private String policyNumber;
}

