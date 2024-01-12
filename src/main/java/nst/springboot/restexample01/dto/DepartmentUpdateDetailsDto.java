/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author student2
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentUpdateDetailsDto implements Serializable {
    private Long id;
    @NotNull
    @Size(min = 2, message = "Broj znakova je najmanje 2.")
    private String name;
    private String shortName;
}
