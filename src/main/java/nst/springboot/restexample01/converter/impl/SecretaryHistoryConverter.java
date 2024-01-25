package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.SecretaryHistory;
import nst.springboot.restexample01.dto.SecretaryHistoryDto;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SecretaryHistoryConverter implements DtoEntityConverter<SecretaryHistoryDto, SecretaryHistory> {

    private final MemberConverter memberConverter;

    @Override
    public SecretaryHistoryDto toDto(SecretaryHistory secretaryHistory) {
        return SecretaryHistoryDto.builder()
                .id(secretaryHistory.getId())
                .startDate(secretaryHistory.getStartDate())
                .endDate(secretaryHistory.getEndDate())
                .departmentName(secretaryHistory.getDepartment().getName())
                .secretary(memberConverter.toDto(secretaryHistory.getSecretary()))
                .build();
    }

    @Override
    public SecretaryHistory toEntity(SecretaryHistoryDto secretaryHistoryDto) {
        var department = Department.builder()
                .name(secretaryHistoryDto.getDepartmentName())
                .build();

        return SecretaryHistory.builder()
                .id(secretaryHistoryDto.getId())
                .startDate(secretaryHistoryDto.getStartDate())
                .endDate(secretaryHistoryDto.getEndDate())
                .department(department)
                .secretary(memberConverter.toEntity(secretaryHistoryDto.getSecretary()))
                .build();
    }

}
