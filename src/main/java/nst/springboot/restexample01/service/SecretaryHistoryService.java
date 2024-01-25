package nst.springboot.restexample01.service;


import nst.springboot.restexample01.dto.SecretaryHistoryDto;

import java.util.List;

public interface SecretaryHistoryService {

    List<SecretaryHistoryDto> getAll();

    void delete(Long id) throws Exception;

    SecretaryHistoryDto findById(Long id) throws Exception;

    List<SecretaryHistoryDto> getSecretaryHistoryOfDepartment(Long departmentId);
}
