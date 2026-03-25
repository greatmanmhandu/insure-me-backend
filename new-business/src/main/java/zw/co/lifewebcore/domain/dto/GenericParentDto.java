package zw.co.lifewebcore.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class GenericParentDto {
    private List<GenericChildrenDto> dependantDto;
    private List<GenericChildrenDto> mainListData;
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
