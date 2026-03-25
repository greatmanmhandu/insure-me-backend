package zw.co.lifewebcore.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zw.co.hcpwebcommons.domain.value.AbstractAuditingEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "savings_form_for_alteration",schema = "hcp_ussd_dev")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SavingsFormForAlteration extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    private String firstname;
    private String surname;
    private String agentName;
    private String agentNumber;
    private String agentUnit;
    private String agentRegion;
    private String address;
    private String planTypeMain;
    private String planTypeDepandance;
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
    private String dateSigned;
    private String actualProd;
    private String currencyVal;
    private String processTypeVal;
    private String productType;
    private String stopOrderFacility;
    private String actionToTake;
    private String linkName;
    private BigDecimal  newPremium;
    private String existingPolicy;
}
