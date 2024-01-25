package nst.springboot.restexample01.converter.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.repository.MemberRepository;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AcademicTitleHistoryConverter implements DtoEntityConverter<AcademicTitleHistoryDto, AcademicTitleHistory> {

    private final MemberRepository memberRepository;

    @Override
    public AcademicTitleHistoryDto toDto(AcademicTitleHistory academicTitleHistory) {
        return AcademicTitleHistoryDto.builder()
                .id(academicTitleHistory.getId())
                .startDate(academicTitleHistory.getStartDate())
                .endDate(academicTitleHistory.getEndDate())
                .memberId(academicTitleHistory.getMember().getId())
                .academicTitle(academicTitleHistory.getAcademicTitle())
                .scientificField(academicTitleHistory.getScientificField())
                .build();
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDto academicTitleHistoryDto) {
        var member = memberRepository.findById(academicTitleHistoryDto.getMemberId());
        return AcademicTitleHistory.builder()
                .id(academicTitleHistoryDto.getId())
                .startDate(academicTitleHistoryDto.getStartDate())
                .endDate(academicTitleHistoryDto.getEndDate())
                .member(member.orElse(null))
                .academicTitle(academicTitleHistoryDto.getAcademicTitle())
                .scientificField(academicTitleHistoryDto.getScientificField())
                .build();
    }
}
