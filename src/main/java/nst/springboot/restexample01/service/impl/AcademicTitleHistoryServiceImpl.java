package nst.springboot.restexample01.service.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.impl.AcademicTitleHistoryConverter;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.repository.AcademicTitleHistoryRepository;
import nst.springboot.restexample01.service.AcademicTitleHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {

    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;


    @Override
    public List<AcademicTitleHistoryDto> getAll() {
        var academicTitleHistoryList = academicTitleHistoryRepository.findAll();
        return academicTitleHistoryList.stream()
                .map(academicTitleHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        var academicHistory = findByAcademicHistoryId(id);
        academicTitleHistoryRepository.delete(academicHistory);
    }


    @Override
    public AcademicTitleHistoryDto findById(Long id) throws Exception {
        var academicHistory = findByAcademicHistoryId(id);
        return academicTitleHistoryConverter.toDto(academicHistory);
    }

    @Override
    public List<AcademicTitleHistoryDto> getAcademicTitleHistoryForMember(Long memberId) {
        var academicTitleHistoryList = academicTitleHistoryRepository.findByMemberIdOrderByStartDate(memberId);
        return academicTitleHistoryList.stream()
                .map(academicTitleHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    private AcademicTitleHistory findByAcademicHistoryId(Long id) throws Exception {
        return academicTitleHistoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Academic history not found with id: " + id));
    }
}
