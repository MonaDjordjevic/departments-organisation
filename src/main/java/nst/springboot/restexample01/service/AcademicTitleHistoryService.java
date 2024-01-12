/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;


import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;

import java.util.List;

/**
 * @author student2
 */
public interface AcademicTitleHistoryService {

    List<AcademicTitleHistoryDto> getAll();

    void delete(Long id) throws Exception;

    AcademicTitleHistoryDto findById(Long id) throws Exception;

    List<AcademicTitleHistoryDto> getAcademicTitleHistoryForMember(Long memberId);
}
