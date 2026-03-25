package zw.co.lifewebcore.request;

import lombok.Data;
import zw.co.lifewebcore.domain.model.DependantDto;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MemberDto {
//    private long memberId;
//    private String policyNumber;
private List<DependantDto> dependantDto;
    private List<DependantDto> mainListData;
    private String agentName;
    private String agentNumber;
    private String agentUnit;
    private String agentRegion;
    private String address;
    private String dateSigned;
    private String actualProd;
    private String currencyVal;
    private String processTypeVal;
    private String productType;
    private BigDecimal mainTotalPremium;
}
