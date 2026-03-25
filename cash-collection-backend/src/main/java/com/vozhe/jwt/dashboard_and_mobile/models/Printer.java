package com.vozhe.jwt.dashboard_and_mobile.models;

import com.vozhe.jwt.enums.SBU;
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
public class Printer extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String printerName;
    private String serialNumber;
//    @Enumerated(EnumType.STRING)
    private String sbu;
    private boolean isAssigned;
}



