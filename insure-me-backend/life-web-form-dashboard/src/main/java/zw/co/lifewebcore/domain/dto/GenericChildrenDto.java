package zw.co.lifewebcore.domain.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class GenericChildrenDto {

    private long id;
    private String fullName;
    private String firstname;
    private String surname;
    private String address;
    private String dob;
    private String gender;
    private String relationShip;
    private BigDecimal burialSpace;
    private BigDecimal  tombStone;
    private BigDecimal  cashBenefit;
    private BigDecimal  totalPremium;
    private String planType;
    private BigDecimal  groceryBenefit;
    private String title;
    private String mobile;
    private String ecNumber;
    private String selectMaritalStatus;
    private String email;
    private String nationalId;
    private String ecoCash;
    private String oneMoney;
    private String monthly;
    private String quarterly;
    private String halfYearly;
    private String annually;
    private String stopOrderFacility;
    private String actionToTake;
    private String linkName;
    private BigDecimal  newPremium;
    private String existingPolicy;
}

