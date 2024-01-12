/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dules
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    private int esbp;
    @NotBlank
    private String department;
}
