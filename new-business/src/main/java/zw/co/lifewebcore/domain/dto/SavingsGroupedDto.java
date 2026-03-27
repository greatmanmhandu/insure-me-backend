package zw.co.lifewebcore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavingsGroupedDto<T> {
    private T mainRecord;
    private List<T> dependants;
}
