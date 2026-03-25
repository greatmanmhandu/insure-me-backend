package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaOfficerRequest {
    private Long id;
    private Long areaOfficeId;
    private Long subAreaOfficeId;
    private String gender;
    private String firstName;
    private String lastName;
    private String password;
    private String nationalId;
    private String areaOfficerNumber;
    private String phoneNumber;
    private String sbu;
    private String dob;
    private String address;
}
