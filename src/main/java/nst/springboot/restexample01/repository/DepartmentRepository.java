/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author student2
 */
//@Transactional(propagation = Propagation.MANDATORY)
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
    public <S extends Department> S save(S entity);

    Optional<Department> findByName(String name);
}
