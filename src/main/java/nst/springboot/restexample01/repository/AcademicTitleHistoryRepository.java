/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author student2
 */
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long>{
    List<AcademicTitleHistory> findByMemberIdOrderByStartDate(Long memberId);
}
