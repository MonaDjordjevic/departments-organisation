/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import nst.springboot.restexample01.converter.impl.DepartmentConverter;
import nst.springboot.restexample01.converter.impl.MemberDetailsConverter;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.DepartmentUpdateDetailsDto;
import nst.springboot.restexample01.dto.MemberDetailsDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.HeadHistoryRepository;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.SecretaryHistoryRepository;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * @author student2
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentConverter departmentConverter;
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;
    private final MemberDetailsConverter memberDetailsConverter;
    private final SecretaryHistoryRepository<SecretaryHistory> secretaryHistoryRepository;
    private final HeadHistoryRepository<HeadHistory> headHistoryRepository;
    private final MemberService memberService;
    private static final int SECRETARY_MANDATE_LENGTH_IN_YEARS = 2;
    private static final int HEAD_MANDATE_LENGTH_IN_YEARS = 2;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) throws Exception {
        validateDepartmentDoesNotExist(departmentDto.getName());
        memberService.validateAndSetMemberFields(departmentDto.getSecretary());
        memberService.validateAndSetMemberFields(departmentDto.getHead());
        var secretary = prepareMember(departmentDto.getSecretary(), departmentDto.getName(), MemberRole.SECRETARY);
        var head = prepareMember(departmentDto.getHead(), departmentDto.getName(), MemberRole.HEAD);

        var department = departmentRepository.save(departmentConverter.toEntity(departmentDto));
        var savedSecretary = memberService.savingMember(secretary);
        var savedHead = memberService.savingMember(head);

        var startDate = LocalDate.now();
        saveHistory(createHistory(MemberRole.SECRETARY, savedSecretary, startDate, department), MemberRole.SECRETARY);
        saveHistory(createHistory(MemberRole.HEAD, savedHead, startDate, department), MemberRole.HEAD);

        var dto = departmentConverter.toDto(department);
        dto.setSecretary(memberDetailsConverter.toDto(savedSecretary));
        dto.setHead(memberDetailsConverter.toDto(savedHead));
        return dto;
    }

    @Override
    public void delete(Long id) throws Exception {
        var department = getDepartment(id);
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentUpdateDetailsDto update(DepartmentUpdateDetailsDto department) throws Exception {
        var existingDepartment = getDepartment(department.getId());
        updateIfNotBlank(existingDepartment::setName, department.getName());
        updateIfNotBlank(existingDepartment::setShortName, department.getShortName());
        var savedDepartment = departmentRepository.save(existingDepartment);
        return DepartmentUpdateDetailsDto.builder().id(savedDepartment.getId()).name(savedDepartment.getName()).shortName(savedDepartment.getShortName()).build();
    }

    @Override
    public DepartmentDto findById(Long id) throws Exception {
        var department = getDepartment(id);
        var departmentDto = departmentConverter.toDto(department);
        getSecretaryAndHeadOfDepartment(departmentDto);
        return departmentDto;
    }

    @Override
    public Optional<DepartmentDto> findByName(String name) {
        Optional<Department> dept = departmentRepository.findByName(name);
        if (dept.isPresent()) {
            Department department = dept.get();
            return Optional.of(departmentConverter.toDto(department));
        }
        return Optional.empty();
    }

    @Override
    public void changeSecretary(Long departmentId, Long secretaryId) throws Exception {
        var startDate = LocalDate.now();
        var department = getDepartment(departmentId);
        var secretary = getDepartmentMember(secretaryId, departmentId, MemberRole.SECRETARY);
        updateRoleAndHistory(MemberRole.SECRETARY, startDate, secretary, department);
    }

    @Override
    public void changeHead(Long departmentId, Long headId) throws Exception {
        var startDate = LocalDate.now();
        var department = getDepartment(departmentId);
        var head = getDepartmentMember(headId, departmentId, MemberRole.HEAD);
        updateRoleAndHistory(MemberRole.HEAD, startDate, head, department);
    }

    @Override
    public List<DepartmentDto> getAll(Pageable pageable) {
        return departmentRepository
                .findAll(pageable).getContent()
                .stream().map(departmentConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto> getAll() {
        var departments = departmentRepository
                .findAll()
                .stream()
                .map(departmentConverter::toDto)
                .collect(Collectors.toList());
        departments.forEach(this::getSecretaryAndHeadOfDepartment);
        return departments;
    }

    private MemberDetailsDto prepareMember(MemberDetailsDto member, String departmentName, MemberRole role) {
        member.setDepartmentName(departmentName);
        member.setMemberRole(role);
        return member;
    }

    private void validateDepartmentDoesNotExist(String departmentName) {
        if (departmentRepository.findByName(departmentName).isPresent()) {
            throw new DepartmentAlreadyExistException("Department with that name already exists!");
        }
    }

    private Department getDepartment(Long departmentId) throws Exception {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new Exception("Department not found with id: " + departmentId));
    }

    private Member getDepartmentMember(Long memberId, Long departmentId, MemberRole memberRole) throws Exception {
        return memberRepository.findByIdAndDepartmentId(memberId, departmentId)
                .orElseThrow(() -> new Exception(format("Member with id %s is not part of department with id %s, so it can not become a %s of that department.", memberId, departmentId, memberRole)));
    }

    private void updateRoleAndHistory(MemberRole memberRole, LocalDate startDate, Member member, Department department) {
        var currentMember = memberRepository.findByMemberRoleAndDepartmentId(memberRole, member.getDepartment().getId());
        var histories = getHistories(memberRole, member.getDepartment().getId());
        var latestHistory = getLatestHistory(histories);
        currentMember.ifPresent(existingMember -> {
            if (latestHistory != null && !startDate.isEqual(latestHistory.getEndDate())) {
                latestHistory.setEndDate(startDate);
                saveHistory(latestHistory, memberRole);
            }
            existingMember.setMemberRole(MemberRole.OTHER);
            memberRepository.save(existingMember);
        });

        member.setMemberRole(memberRole);
        memberRepository.save(member);
        saveHistory(createHistory(memberRole, member, startDate, department), memberRole);
    }

    private List<? extends History> getHistories(MemberRole role, Long departmentId) {
        return (role == MemberRole.SECRETARY) ?
                secretaryHistoryRepository.findSecretaryHistoriesByDepartmentIdOrderByStartDate(departmentId) :
                headHistoryRepository.findByDepartmentIdOrderByStartDate(departmentId);
    }

    private <S extends History> S getLatestHistory(List<S> histories) {
        return histories.stream().reduce((first, second) -> second).orElse(null);
    }

    private void saveHistory(History history, MemberRole memberRole) {
        if (memberRole == MemberRole.SECRETARY) {
            secretaryHistoryRepository.save(history);
        } else {
            headHistoryRepository.save(history);
        }
    }

    private History createHistory(MemberRole role, Member member, LocalDate startDate, Department department) {
        return (role == MemberRole.SECRETARY) ?
                SecretaryHistory.builder().secretary(member).department(department).startDate(startDate).endDate(startDate.plusYears(SECRETARY_MANDATE_LENGTH_IN_YEARS)).build() :
                HeadHistory.builder().head(member).department(department).startDate(startDate).endDate(startDate.plusYears(HEAD_MANDATE_LENGTH_IN_YEARS)).build();
    }

    private void getSecretaryAndHeadOfDepartment(DepartmentDto departmentDto) {
        var secretary = memberRepository.findByMemberRoleAndDepartmentId(MemberRole.SECRETARY, departmentDto.getId());
        secretary.ifPresent(member1 -> departmentDto.setSecretary(memberDetailsConverter.toDto(member1)));
        var head = memberRepository.findByMemberRoleAndDepartmentId(MemberRole.HEAD, departmentDto.getId());
        head.ifPresent(member1 -> departmentDto.setHead(memberDetailsConverter.toDto(member1)));
    }

    private void updateIfNotBlank(Consumer<String> setter, String value) {
        if (StringUtils.isNotBlank(value)) {
            setter.accept(value);
        }
    }
}
