package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String fullNames;
    private String firstName;
    private String lastName;
    private String authorities;
    private String sbu;
    private String agentNumber;
    private String message;


}

