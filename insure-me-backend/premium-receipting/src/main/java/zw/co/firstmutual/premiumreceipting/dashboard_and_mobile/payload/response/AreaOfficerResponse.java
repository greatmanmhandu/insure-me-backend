package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaOfficerResponse {
    private AreaOfficer areaOfficer;
    private AreaOffice areaOffice;
    private SubAreaOffice subAreaOffice;
}
