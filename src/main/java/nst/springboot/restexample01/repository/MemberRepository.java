/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author student2
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPersonalNo(String personalNo);

    Optional<Member> findByIdAndDepartmentId(Long memberId, Long departmentId);

    Optional<Member> findByMemberRoleAndDepartmentId(MemberRole memberRole, Long departmentId);
}
