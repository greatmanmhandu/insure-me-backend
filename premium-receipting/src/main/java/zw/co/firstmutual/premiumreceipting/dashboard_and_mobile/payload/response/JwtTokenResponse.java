package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtTokenResponse implements Serializable {

    private String accessToken;
    private String tokenType = "Bearer";
    private String code;
    private UserDTO user;
    private ShiftLogin shiftLogin;

    public JwtTokenResponse() {
    }

    public JwtTokenResponse(String accessToken, UserDTO user, String code) {
        this.accessToken = accessToken;
        this.user = user;
        this.code = code;
    }

    public JwtTokenResponse(String accessToken, UserDTO user, String code,ShiftLogin shiftLogin) {
        this.accessToken = accessToken;
        this.user = user;
        this.code = code;
        this.shiftLogin = shiftLogin;
    }


}

