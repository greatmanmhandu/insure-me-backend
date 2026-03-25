package zw.co.lifewebcore.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MedicalDetailEntryDto {
    private int questionNo;
    private String nature;
    private String date;
    private String doctor;
    private String lastSymptoms;
}
