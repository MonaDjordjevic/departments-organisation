/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.HeadHistory;
import nst.springboot.restexample01.dto.HeadHistoryDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author student2
 */

@Component
@AllArgsConstructor
public class HeadHistoryConverter implements DtoEntityConverter<HeadHistoryDto, HeadHistory> {

    private final MemberConverter memberConverter;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public HeadHistoryDto toDto(HeadHistory headHistory) {
        return HeadHistoryDto.builder()
                .id(headHistory.getId())
                .startDate(formatDate(headHistory.getStartDate()))
                .endDate(formatDate(headHistory.getEndDate()))
                .departmentName(headHistory.getDepartment().getName())
                .head(memberConverter.toDto(headHistory.getHead()))
                .build();
    }

    @Override
    public HeadHistory toEntity(HeadHistoryDto headHistoryDto) {
        return HeadHistory.builder()
                .id(headHistoryDto.getId())
                .startDate(headHistoryDto.getStartDate())
                .endDate(headHistoryDto.getEndDate())
                .build();
    }

    private LocalDate formatDate(LocalDate date) {
        String formattedDate = date.format(formatter);
        return LocalDate.parse(formattedDate, formatter);
    }
}
