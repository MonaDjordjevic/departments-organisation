package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcademicTitleHistoryUpdateDto implements Serializable {

    private LocalDate startDate;
    @NotBlank
    private String academicTitle;
    @NotBlank
    private String scientificField;
}