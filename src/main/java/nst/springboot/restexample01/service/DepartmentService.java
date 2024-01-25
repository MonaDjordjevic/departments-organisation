package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.DepartmentUpdateDetailsDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;

    void changeHead(Long departmentId, Long headId, LocalDate startDate) throws Exception;

    List<DepartmentDto> getAll(Pageable pageable);

    List<DepartmentDto> getAll();

    void delete(Long id) throws Exception;

    DepartmentUpdateDetailsDto update(Long id, DepartmentUpdateDetailsDto department) throws Exception;

    DepartmentDto findById(Long id) throws Exception;

    void changeSecretary(Long departmentId, Long secretaryId, LocalDate startDate) throws Exception;
}
