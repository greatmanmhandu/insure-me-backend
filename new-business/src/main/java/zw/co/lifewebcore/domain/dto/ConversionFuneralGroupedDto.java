package zw.co.lifewebcore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.lifewebcore.domain.model.ConversionForFuneralProducts;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversionFuneralGroupedDto {
    private ConversionForFuneralProducts mainRecord;
    private List<ConversionForFuneralProducts> dependants;
}
