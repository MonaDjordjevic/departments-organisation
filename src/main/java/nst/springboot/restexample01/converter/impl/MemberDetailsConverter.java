/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.MemberDetailsDto;
import nst.springboot.restexample01.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class MemberDetailsConverter implements DtoEntityConverter<MemberDetailsDto, Member> {

    private final DepartmentRepository departmentRepository;

    @Override
    public MemberDetailsDto toDto(Member entity) {
        return MemberDetailsDto.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .personalNo(entity.getPersonalNo())
                .departmentName(entity.getDepartment().getName())
                .academicTitle(entity.getAcademicTitle())
                .educationTitle(entity.getEducationTitle())
                .scientificField(entity.getScientificField())
                .memberRole(entity.getMemberRole())
                .build();
    }


    @Override
    public Member toEntity(MemberDetailsDto dto) {
        var department = departmentRepository.findByName(dto.getDepartmentName());
        return Member.builder()
                .id(dto.getId())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .personalNo(dto.getPersonalNo())
                .academicTitle(dto.getAcademicTitle())
                .department(department.orElse(null))
                .educationTitle(dto.getEducationTitle())
                .scientificField(dto.getScientificField())
                .memberRole(dto.getMemberRole())
                .build();
    }
}
