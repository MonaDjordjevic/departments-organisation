package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.dto.AcademicTitleHistoryUpdateDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.exception.AddingMemberToDepartmentThatNotExistException;
import nst.springboot.restexample01.exception.MemberAlreadyExistException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import nst.springboot.restexample01.service.AcademicTitleHistoryService;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;
    private final AcademicTitleHistoryService academicTitleHistoryService;

    @PostMapping()
    public ResponseEntity<MemberDto> save(@Valid @RequestBody MemberDto member) throws Exception {
        var memberDto = memberService.save(member);
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAll() {
        var members = memberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable Long id) throws Exception {
        return memberService.findById(id)
                .map(memberConverter::toDto)
                .map(memberDto -> ResponseEntity.ok().body(memberDto))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> update(@PathVariable Long id, @RequestBody MemberDto memberDto) throws Exception {
        var member = memberService.updateMember(id, memberDto);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);
    }

    @PostMapping("/{memberId}/academic-title-update")
    public ResponseEntity<String> changeAcademicTitle(@PathVariable Long memberId, @RequestBody AcademicTitleHistoryUpdateDto academicTitleHistoryUpdateDto) throws Exception {
        memberService.changeAcademicTitle(memberId, academicTitleHistoryUpdateDto);
        return ResponseEntity.ok("Member's academic title changed successfully.");
    }

    @GetMapping("/{memberId}/academic-title-history")
    public List<AcademicTitleHistoryDto> getAcademicTitleHistory(@PathVariable Long memberId) {
        return academicTitleHistoryService.getAcademicTitleHistoryForMember(memberId);
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<MyErrorDetails> handleException(MemberAlreadyExistException e) {
        System.out.println("-----------pozvana metoda za obradu izuzetka u member kontroleru -------------");
        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddingMemberToDepartmentThatNotExistException.class)
    public ResponseEntity<MyErrorDetails> handleException(AddingMemberToDepartmentThatNotExistException e) {
        System.out.println("-----------pozvana metoda za obradu izuzetka u member kontroleru -------------");
        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }
}
