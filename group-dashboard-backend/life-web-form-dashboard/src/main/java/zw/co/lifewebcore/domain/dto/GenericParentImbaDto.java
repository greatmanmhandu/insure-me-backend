package zw.co.lifewebcore.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GenericParentImbaDto {
    private List<GenericChildrenImbaDto> dependantDto;
    private List<GenericChildrenImbaDto> mainListData;
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
    // New fields from the payload
    private Map<String, String> medicalAnswers;
    private List<MedicalDetailEntryDto> medicalDetailEntries;
}
