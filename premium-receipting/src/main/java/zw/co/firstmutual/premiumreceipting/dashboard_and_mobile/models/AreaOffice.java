package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import zw.co.firstmutual.premiumreceipting.enums.SBU;
import zw.co.firstmutual.premiumreceipting.enums.Status;
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
public class AreaOffice extends GenericAuditTrail<String> {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean activeStatus;
    private String sbu;


}




