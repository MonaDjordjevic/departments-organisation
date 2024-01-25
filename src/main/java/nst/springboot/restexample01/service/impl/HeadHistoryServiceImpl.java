package nst.springboot.restexample01.service.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.impl.HeadHistoryConverter;
import nst.springboot.restexample01.domain.HeadHistory;
import nst.springboot.restexample01.dto.HeadHistoryDto;
import nst.springboot.restexample01.repository.HeadHistoryRepository;
import nst.springboot.restexample01.service.HeadHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeadHistoryServiceImpl implements HeadHistoryService {

    private final HeadHistoryRepository<HeadHistory> headHistoryRepository;
    private final HeadHistoryConverter headHistoryConverter;

    @Override
    public List<HeadHistoryDto> getAll() {
        var headHistoryList = headHistoryRepository.findAll();
        return headHistoryList.stream()
                .map(headHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        var headHistory = findByHeadHistoryId(id);
        headHistoryRepository.delete(headHistory);
    }


    @Override
    public HeadHistoryDto findById(Long id) throws Exception {
        var headHistory = findByHeadHistoryId(id);
        return headHistoryConverter.toDto(headHistory);
    }

    @Override
    public List<HeadHistoryDto> getHeadHistoryOfDepartment(Long departmentId) {
        Assert.hasText(String.valueOf(departmentId), "Department id must not be null.");
        var headHistoryList = headHistoryRepository.findByDepartmentIdOrderByStartDate(departmentId);
        return headHistoryList.stream()
                .map(headHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    private HeadHistory findByHeadHistoryId(Long id) throws Exception {
        return headHistoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Head history not found with id: " + id));
    }
}
