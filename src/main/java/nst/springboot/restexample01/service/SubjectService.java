/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.SubjectDto;

import java.util.List;

/**
 * @author student2
 */
public interface SubjectService {
    SubjectDto save(SubjectDto subjectDto) throws Exception;

    List<SubjectDto> getAll();

    void delete(Long id) throws Exception;

    SubjectDto update(SubjectDto subjectDto) throws Exception;

    SubjectDto findById(Long id) throws Exception;
}
