package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Using Lombok annotations to keep boilerplate code minimal
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyUpdateEntry {
    private Long id;
    private String policyNumber;
}
