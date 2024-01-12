package nst.springboot.restexample01.service.impl;

import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.impl.SecretaryHistoryConverter;
import nst.springboot.restexample01.domain.SecretaryHistory;
import nst.springboot.restexample01.dto.SecretaryHistoryDto;
import nst.springboot.restexample01.repository.SecretaryHistoryRepository;
import nst.springboot.restexample01.service.SecretaryHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SecretaryHistoryServiceImpl implements SecretaryHistoryService {

    private final SecretaryHistoryRepository<SecretaryHistory> secretaryHistoryRepository;
    private final SecretaryHistoryConverter secretaryHistoryConverter;


    @Override
    public List<SecretaryHistoryDto> getAll() {
        var secretaryHistoryList = secretaryHistoryRepository.findAll();
        return secretaryHistoryList.stream().map(secretaryHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        var secretaryHistory = findBySecretaryHistoryId(id);
        secretaryHistoryRepository.delete(secretaryHistory);
    }

    @Override
    public SecretaryHistoryDto findById(Long id) throws Exception {
        var secretaryHistory = findBySecretaryHistoryId(id);
        return secretaryHistoryConverter.toDto(secretaryHistory);
    }

    @Override
    public List<SecretaryHistoryDto> getSecretaryHistoryOfDepartment(Long departmentId) {
        var secretaryHistories = secretaryHistoryRepository.findSecretaryHistoriesByDepartmentIdOrderByStartDate(departmentId);
        return secretaryHistories.stream()
                .map(secretaryHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    private SecretaryHistory findBySecretaryHistoryId(Long id) throws Exception {
        return secretaryHistoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Secretary history not found with id: " + id));
    }
}
