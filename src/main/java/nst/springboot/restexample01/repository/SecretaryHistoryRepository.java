/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.History;
import nst.springboot.restexample01.domain.SecretaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author student2
 */
public interface SecretaryHistoryRepository<T extends History> extends JpaRepository<SecretaryHistory, Long> {

    List<SecretaryHistory> findSecretaryHistoriesByDepartmentIdOrderByStartDate(Long departmentId);

    public <S extends History> S save(S entity);

}
