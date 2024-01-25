package nst.springboot.restexample01.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class HeadHistoryDto implements Serializable {

    private Long id;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
    @NotEmpty
    private String departmentName;
    @NotNull
    @Valid
    private MemberDto head;
}

