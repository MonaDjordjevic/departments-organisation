/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class AcademicTitleHistoryConverter implements DtoEntityConverter<AcademicTitleHistoryDto, AcademicTitleHistory> {

    private final MemberConverter memberConverter;

    @Override
    public AcademicTitleHistoryDto toDto(AcademicTitleHistory academicTitleHistory) {
        return AcademicTitleHistoryDto.builder()
                .id(academicTitleHistory.getId())
                .startDate(formatDate(academicTitleHistory.getStartDate()))
                .endDate(formatDate(academicTitleHistory.getEndDate()))
                .member(memberConverter.toDto(academicTitleHistory.getMember()))
                .academicTitle(academicTitleHistory.getAcademicTitle())
                .scientificField(academicTitleHistory.getScientificField())
                .build();
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDto academicTitleHistoryDto) {
        return AcademicTitleHistory.builder()
                .id(academicTitleHistoryDto.getId())
                .startDate(academicTitleHistoryDto.getStartDate())
                .endDate(academicTitleHistoryDto.getEndDate())
                .member(memberConverter.toEntity(academicTitleHistoryDto.getMember()))
                .academicTitle(academicTitleHistoryDto.getAcademicTitle())
                .scientificField(academicTitleHistoryDto.getScientificField())
                .build();
    }

    private LocalDate formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);
        return LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
