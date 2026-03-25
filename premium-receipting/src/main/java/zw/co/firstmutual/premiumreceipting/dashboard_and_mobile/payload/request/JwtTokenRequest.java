package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Data
public class JwtTokenRequest implements Serializable{
//    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
    private String agentNumber;

}

