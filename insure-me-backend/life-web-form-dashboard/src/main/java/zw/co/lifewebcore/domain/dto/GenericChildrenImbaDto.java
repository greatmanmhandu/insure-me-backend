package zw.co.lifewebcore.domain.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GenericChildrenImbaDto {

    private long id;
    private String fullName;
    private String firstname;
    private String surname;
    private String address;
    private String dob;
    private String gender;
    private String relationShip;
    private BigDecimal totalPremium;
    private String planType; // Retained as it might be used by the frontend as planTypeM
    private String title;
    private String mobile;
    private String ecNumber;
    private String selectMaritalStatus;
    private String email;
    private String nationalId;
    private String stopOrderFacility;
    private String linkName;

    // New fields from the payload
    private String occupation;
    private String employerName;
    private BigDecimal monthlyIncome;
    private BigDecimal sumAssured;
    private BigDecimal deposit;
    private String sourceOfFunds;
    private String term; // Can be "5" or "10" from the payload
    private String selectedPaymentMethod;
    private String specifyForOther;
    private String cash; // "Yes" or "No"
    private String cashBenefit; // "Yes" or "No"
    private String paymentFrequency;
    private String herdName;
}
