package nst.springboot.restexample01.service;


import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.AcademicTitleHistoryUpdateDto;
import nst.springboot.restexample01.dto.BaseMemberDto;
import nst.springboot.restexample01.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    MemberDto save(MemberDto memberDto) throws Exception;

    Member savingMember(BaseMemberDto memberDto) throws Exception;

    void validateAndSetMemberFields(BaseMemberDto memberDto) throws Exception;

    List<MemberDto> getAll();

    void delete(Long id) throws Exception;

    Optional<Member> findById(Long id);

    void changeAcademicTitle(Long memberId, AcademicTitleHistoryUpdateDto academicTitleHistoryUpdateDto) throws Exception;

    MemberDto updateMember(Long id, MemberDto memberDto) throws Exception;
}
