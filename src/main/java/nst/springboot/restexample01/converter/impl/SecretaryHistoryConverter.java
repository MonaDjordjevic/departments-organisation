/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.SecretaryHistory;
import nst.springboot.restexample01.dto.SecretaryHistoryDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class SecretaryHistoryConverter implements DtoEntityConverter<SecretaryHistoryDto, SecretaryHistory> {

    private final MemberConverter memberConverter;

    @Override
    public SecretaryHistoryDto toDto(SecretaryHistory secretaryHistory) {
        return SecretaryHistoryDto.builder()
                .id(secretaryHistory.getId())
                .startDate(formatDate(secretaryHistory.getStartDate()))
                .endDate(formatDate(secretaryHistory.getEndDate()))
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

    private LocalDate formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);
        return LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
