/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.HeadHistory;
import nst.springboot.restexample01.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author student2
 */
public interface HeadHistoryRepository<T extends History> extends JpaRepository<HeadHistory, Long> {

    List<T> findByDepartmentIdOrderByStartDate(Long departmentId);

    public <S extends History> S save(S entity);

}
