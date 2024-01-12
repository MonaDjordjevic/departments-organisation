/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nst.springboot.restexample01.domain.MemberRole;

import java.io.Serializable;

/**
 * @author Dules
 */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto extends BaseMemberDto{

    @NotBlank
    private String departmentName;
}
