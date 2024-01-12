package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SecretaryHistoryDto implements Serializable {

    private Long id;
    @NotNull
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate endDate;
    @NotBlank
    private String departmentName;
    @NotNull
    @Valid
    private MemberDto secretary;
}
