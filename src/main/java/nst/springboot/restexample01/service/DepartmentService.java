/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.DepartmentUpdateDetailsDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author student2
 */
public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;

    void changeHead(Long departmentId, Long headId) throws Exception;

    List<DepartmentDto> getAll(Pageable pageable);

    List<DepartmentDto> getAll();

    void delete(Long id) throws Exception;

    DepartmentUpdateDetailsDto update(DepartmentUpdateDetailsDto department) throws Exception;

    DepartmentDto findById(Long id) throws Exception;

    Optional<DepartmentDto> findByName(String name);

    void changeSecretary(Long departmentId, Long secretaryId) throws Exception;
}
