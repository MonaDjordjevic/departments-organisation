package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nst.springboot.restexample01.domain.MemberRole;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseMemberDto implements Serializable {

    private Long id;
    @NotBlank
    private String firstname;
    private String lastname;

    @NotBlank
    @Pattern(regexp = "\\d{13}")
    private String personalNo;//JMBG

    private String academicTitle;
    private String educationTitle;
    private String scientificField;

    @JsonIgnore
    @Builder.Default
    private MemberRole memberRole = MemberRole.OTHER;
}