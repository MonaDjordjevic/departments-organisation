package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.HeadHistory;
import nst.springboot.restexample01.dto.HeadHistoryDto;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeadHistoryConverter implements DtoEntityConverter<HeadHistoryDto, HeadHistory> {

    private final MemberConverter memberConverter;

    @Override
    public HeadHistoryDto toDto(HeadHistory headHistory) {
        return HeadHistoryDto.builder()
                .id(headHistory.getId())
                .startDate(headHistory.getStartDate())
                .endDate(headHistory.getEndDate())
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
}
