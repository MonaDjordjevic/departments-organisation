package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.DepartmentUpdateDetailsDto;
import nst.springboot.restexample01.dto.HeadHistoryDto;
import nst.springboot.restexample01.dto.SecretaryHistoryDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.service.HeadHistoryService;
import nst.springboot.restexample01.service.SecretaryHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final SecretaryHistoryService secretaryHistoryService;
    private final HeadHistoryService headHistoryService;

    @PostMapping()
    public ResponseEntity<DepartmentDto> save(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        DepartmentDto deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<DepartmentDto>> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {

        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<DepartmentDto> departments = departmentService.getAll(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        departmentService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);
    }

    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public ResponseEntity<MyErrorDetails> handleException(DepartmentAlreadyExistException e) {
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");
        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{departmentId}/secretary-change")
    public ResponseEntity<String> changeSecretary(
            @PathVariable Long departmentId,
            @RequestParam Long secretaryId, @RequestParam LocalDate startDate) throws Exception {

        departmentService.changeSecretary(departmentId, secretaryId, startDate);
        return ResponseEntity.ok("Department's secretary changed successfully.");
    }

    @PostMapping("/{departmentId}/head-change")
    public ResponseEntity<String> changeHead(
            @PathVariable Long departmentId,
            @RequestParam Long headId, @RequestParam LocalDate startDate) throws Exception {

        departmentService.changeHead(departmentId, headId, startDate);
        return ResponseEntity.ok("Department's head changed successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentUpdateDetailsDto> update(@PathVariable Long id, @RequestBody DepartmentUpdateDetailsDto departmentDto) throws Exception {
        var department = departmentService.update(id, departmentDto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/secretary-history")
    public ResponseEntity<List<SecretaryHistoryDto>> findSecretaryHistoryOfDepartment(@PathVariable Long departmentId) {
        var secretaryHistoryDtoList = secretaryHistoryService.getSecretaryHistoryOfDepartment(departmentId);
        return new ResponseEntity<>(secretaryHistoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/head-history")
    public ResponseEntity<List<HeadHistoryDto>> findHeadHistoryOfDepartment(@PathVariable Long departmentId) {
        var headHistoryDtoList = headHistoryService.getHeadHistoryOfDepartment(departmentId);
        return new ResponseEntity<>(headHistoryDtoList, HttpStatus.OK);
    }
}
