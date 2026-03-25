package com.vozhe.jwt.dashboard_and_mobile.payload.request;

import com.vozhe.jwt.enums.SBU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String idNumber;
    private String address;
    private Date DOB;
    private String dateOfBirth;
    private String secondPhoneNumber;
    private String gender;
    private String password;
    private String username;
    private String roles;
    private SBU sbu;
}
