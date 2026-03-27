package zw.co.lifewebcore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.lifewebcore.domain.model.AlterationFormForfuneral;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlterationFuneralGroupedDto {
    private AlterationFormForfuneral mainRecord;
    private List<AlterationFormForfuneral> dependants;
}
