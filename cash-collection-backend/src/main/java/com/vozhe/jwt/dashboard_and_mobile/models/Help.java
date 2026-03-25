package com.vozhe.jwt.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Help extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String agentNumber;
    private String sbu;
    private String helpText;
}

