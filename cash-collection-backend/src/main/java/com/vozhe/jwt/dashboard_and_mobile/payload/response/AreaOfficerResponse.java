package com.vozhe.jwt.dashboard_and_mobile.payload.response;

import com.vozhe.jwt.dashboard_and_mobile.models.*;
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