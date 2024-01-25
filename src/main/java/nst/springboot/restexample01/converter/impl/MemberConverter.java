package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemberConverter implements DtoEntityConverter<MemberDto, Member> {

    private final DepartmentRepository departmentRepository;

    @Override
    public MemberDto toDto(Member entity) {
        return MemberDto.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .personalNo(entity.getPersonalNo())
                .departmentId(entity.getDepartment().getId())
                .academicTitle(entity.getAcademicTitle())
                .educationTitle(entity.getEducationTitle())
                .scientificField(entity.getScientificField())
                .memberRole(entity.getMemberRole())
                .build();
    }

    @Override
    public Member toEntity(MemberDto dto) {
        var department = departmentRepository.findById(dto.getDepartmentId());
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
