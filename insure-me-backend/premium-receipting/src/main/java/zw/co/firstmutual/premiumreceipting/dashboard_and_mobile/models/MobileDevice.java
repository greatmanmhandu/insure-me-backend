package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
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
public class MobileDevice extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String mobileDeviceName;
    private String serialNumber;
    private String sbu;
    private boolean isAssigned;
}




