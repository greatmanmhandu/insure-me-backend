package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolderRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String policyNumber;
    private String idNumber;
    private String address;
    private String dob;
    private String sbu;
    private String productType;
    private String policyHolderContractPremium;
    private String createdBy;
    private String sumAssured;
}
