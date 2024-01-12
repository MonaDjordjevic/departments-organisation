/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.stereotype.Component;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department> {

    @Override
    public DepartmentDto toDto(Department entity) {
        return DepartmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .shortName(entity.getShortName())
                .build();
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        return Department.builder()
                .id(dto.getId())
                .name(dto.getName())
                .shortName(dto.getShortName())
                .build();
    }
}
