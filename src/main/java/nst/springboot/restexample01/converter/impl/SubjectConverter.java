/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Subject;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class SubjectConverter implements DtoEntityConverter<SubjectDto, Subject> {

    private final DepartmentRepository departmentRepository;

    @Override
    public SubjectDto toDto(Subject entity) {
        return SubjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .esbp(entity.getEsbp())
                .department(entity.getDepartment().getName())
                .build();
    }

    @Override
    public Subject toEntity(SubjectDto dto) {
        var department = departmentRepository.findByName(dto.getDepartment());

        return Subject.builder()
                .id(dto.getId())
                .name(dto.getName())
                .esbp(dto.getEsbp())
                .department(department.orElse(null))
                .build();
    }
}
