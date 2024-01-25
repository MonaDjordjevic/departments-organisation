/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.impl.SubjectConverter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.Subject;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.SubjectRepository;
import nst.springboot.restexample01.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author student2
 */
@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectConverter subjectConverter;
    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        var departmentDto = departmentRepository.findByName(subjectDto.getDepartment());
        checkValidityOfFields(subjectDto, departmentDto);
        var subject = subjectConverter.toEntity(subjectDto);

        var saved = subjectRepository.save(subject);
        return subjectConverter.toDto(saved);
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository
                .findAll()
                .stream()
                .map(subjectConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectRepository.delete(subject.get());
        } else {
            throw new Exception("Subject does not exist!");
        }
    }

    @Override
    public SubjectDto update(Long id, SubjectDto subjectDto) throws Exception {
        var existingSubject = findSubjectById(id);
        if (!subjectDto.getName().isBlank()) {
            existingSubject.setName(subjectDto.getName());
        }
        if (!String.valueOf(subjectDto.getEsbp()).isBlank()) {
            existingSubject.setEsbp(subjectDto.getEsbp());
        }
        if (!subjectDto.getDepartment().isBlank()) {
            var departmentDto = departmentRepository.findByName(subjectDto.getDepartment());
            if (departmentDto.isEmpty()) {
                throw new Exception("Can not add subject to department that does not exist.");
            }
            existingSubject.setDepartment(departmentDto.get());
        }
        var savedSubject = subjectRepository.save(existingSubject);
        return subjectConverter.toDto(savedSubject);
    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        var subject = findSubjectById(id);
        return subjectConverter.toDto(subject);
    }

    private void checkValidityOfFields(SubjectDto subjectDto, Optional<Department> departmentDto) throws Exception {
        if (departmentDto.isEmpty()) {
            throw new Exception("Can not add subject to department that does not exist.");
        }
        if (subjectRepository.findByName(subjectDto.getName()).isPresent()) {
            throw new Exception("Subject with that name already exist.");
        }
    }

    private Subject findSubjectById(Long subjectId) throws Exception {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new Exception("Subject not found with id: " + subjectId));
    }

}
