package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.SBU;
import com.vozhe.jwt.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubAreaOffice extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long areaOfficeId;
    private String name;
    private boolean activeStatus;
    private String sbu;
}



