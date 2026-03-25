package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicySearchRequest {
    private String searchID;
    private String searchPhone;
}

