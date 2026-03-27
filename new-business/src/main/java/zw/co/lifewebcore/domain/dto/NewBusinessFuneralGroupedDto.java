package zw.co.lifewebcore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.lifewebcore.domain.model.NewBusinessFuneralForEfmlandELP;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewBusinessFuneralGroupedDto {
    private NewBusinessFuneralForEfmlandELP mainRecord;
    private List<NewBusinessFuneralForEfmlandELP> dependants;
}
