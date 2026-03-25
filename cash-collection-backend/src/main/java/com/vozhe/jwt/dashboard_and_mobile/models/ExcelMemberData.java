package com.vozhe.jwt.dashboard_and_mobile.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member_data_excel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExcelMemberData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_number")
    private String PolicyNumber;

    @Column(name = "premium_payer_firstname")
    private String PremiumPayerFirstName;

    @Column(name = "premium_payer_surname")
    private String PremiumPayerSurname;

    @Column(name = "id_number")
    private String IDNumber;

    @Column(name = "sum_assured_plan_type")
    private String SumAssuredPlanType;

    @Column(name = "product_type")
    private String ProductType;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "gender")
    private String Gender;

    @Column(name = "date_of_birth")
    private String DateOfBirth;

    @Column(name = "address")
    private String Address;

    // Constructors, getters, setters
}
