/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;


import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.BaseMemberDto;
import nst.springboot.restexample01.dto.MemberDto;

import java.util.List;
import java.util.Optional;

/**
 * @author student2
 */
public interface MemberService {
    MemberDto save(MemberDto memberDto) throws Exception;

    Member savingMember(BaseMemberDto memberDto) throws Exception;

    void validateAndSetMemberFields(BaseMemberDto memberDto) throws Exception;

    List<MemberDto> getAll();

    void delete(Long id) throws Exception;

    Optional<Member> findById(Long id);

    Optional<MemberDto> findByPersonalNo(String personalNo);

    void changeAcademicTitle(Long memberId, String academicTitleEnum) throws Exception;

    MemberDto updateMember(MemberDto memberDto) throws Exception;
}
