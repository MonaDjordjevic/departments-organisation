package nst.springboot.restexample01.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.converter.impl.MemberDetailsConverter;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.BaseMemberDto;
import nst.springboot.restexample01.dto.MemberDetailsDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.exception.AddingMemberToDepartmentThatNotExistException;
import nst.springboot.restexample01.exception.MemberAlreadyExistException;
import nst.springboot.restexample01.repository.AcademicTitleHistoryRepository;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final MemberDetailsConverter memberDetailsConverter;
    private final DepartmentRepository departmentRepository;
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;

    @Override
    public MemberDto save(MemberDto memberDto) throws Exception {
        validateAndSetMemberFields(memberDto);
        var departmentDto = departmentRepository.findByName(memberDto.getDepartmentName());
        if (departmentDto.isEmpty()) {
            throw new AddingMemberToDepartmentThatNotExistException("Can not add member to department that does not exist.");
        }
        return memberConverter.toDto(savingMember(memberDto));
    }

    @Override
    public Member savingMember(BaseMemberDto memberDto) {
        if (memberRepository.findByPersonalNo(memberDto.getPersonalNo()).isPresent()) {
            throw new MemberAlreadyExistException("Member already exists.");
        }
        if (memberDto instanceof MemberDto) {
            return memberRepository.save(memberConverter.toEntity((MemberDto) memberDto));
        }
        return memberRepository.save(memberDetailsConverter.toEntity((MemberDetailsDto) memberDto));
    }

    @Override
    public void validateAndSetMemberFields(BaseMemberDto memberDto) throws Exception {
        var academicTitle = AcademicTitle.fromString(memberDto.getAcademicTitle());
        var scientificField = ScientificField.fromString(memberDto.getScientificField());
        var educationTitle = EducationTitle.fromString(memberDto.getEducationTitle());
        memberDto.setAcademicTitle(academicTitle.getName());
        memberDto.setScientificField(scientificField.getName());
        memberDto.setEducationTitle(educationTitle.getName());
    }

    @Override
    public List<MemberDto> getAll() {
        var members = memberRepository.findAll();
        return members.stream().map(memberConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        var member = findMemberById(id);
        memberRepository.delete(member);
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto) throws Exception {
        var existingMember = findMemberById(memberDto.getId());
        updateIfNotBlank(existingMember::setFirstname, memberDto.getFirstname());
        updateIfNotBlank(existingMember::setLastname, memberDto.getLastname());
        updateIfNotBlank(existingMember::setPersonalNo, memberDto.getPersonalNo());
        if (!memberDto.getEducationTitle().isBlank()) {
            var educationTitle = EducationTitle.fromString(memberDto.getEducationTitle());
            existingMember.setEducationTitle(educationTitle.getName());
        }
        if (!memberDto.getAcademicTitle().isBlank()) {
            var academicTitle = AcademicTitle.fromString(memberDto.getAcademicTitle());
            existingMember.setAcademicTitle(academicTitle.getName());
        }
        if (!memberDto.getScientificField().isBlank()) {
            var scientificField = ScientificField.fromString(memberDto.getScientificField());
            existingMember.setScientificField(scientificField.getName());
        }
        if (!memberDto.getDepartmentName().isBlank()) {
            var departmentDto = departmentRepository.findByName(memberDto.getDepartmentName());
            if (departmentDto.isEmpty()) {
                throw new AddingMemberToDepartmentThatNotExistException("Can not add member to department that does not exist.");
            }
            existingMember.setDepartment(departmentDto.get());
        }

        var savedMember = memberRepository.save(existingMember);
        return memberConverter.toDto(savedMember);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<MemberDto> findByPersonalNo(String personalNo) {
        var member = memberRepository.findByPersonalNo(personalNo);
        return member.map(memberConverter::toDto);
    }

    private void updateIfNotBlank(Consumer<String> setter, String value) {
        if (StringUtils.isNotBlank(value)) {
            setter.accept(value);
        }
    }

    public void changeAcademicTitle(Long memberId, String academicTitleString) throws Exception {
        var startDate = LocalDate.now();
        var member = findMemberById(memberId);
        var academicTitle = AcademicTitle.fromString(academicTitleString);
        member.setAcademicTitle(academicTitle.name());
        memberRepository.save(member);
        var academicTitleHistories = academicTitleHistoryRepository.findByMemberIdOrderByStartDate(memberId);
        var latestAcademicTitleHistory = academicTitleHistories.stream().reduce((first, second) -> second).orElse(null);
        if (latestAcademicTitleHistory != null) {
            latestAcademicTitleHistory.setEndDate(startDate);
            academicTitleHistoryRepository.save(latestAcademicTitleHistory);
        }
        var history = AcademicTitleHistory
                .builder()
                .member(member)
                .startDate(startDate)
                .academicTitle(academicTitle.name())
                .scientificField(member.getScientificField())
                .build();
        academicTitleHistoryRepository.save(history);
    }

    private Member findMemberById(Long memberId) throws Exception {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception("Member not found with id: " + memberId));
    }

}
