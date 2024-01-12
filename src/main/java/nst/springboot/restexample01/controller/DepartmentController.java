/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.DepartmentUpdateDetailsDto;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import nst.springboot.restexample01.service.DepartmentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author student2
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/save")
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

    @GetMapping("/findDepartment/{id}")
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

    @PostMapping("/{departmentId}/change-secretary")
    public ResponseEntity<String> changeSecretary(
            @PathVariable Long departmentId,
            @RequestParam Long secretaryId) throws Exception {

        departmentService.changeSecretary(departmentId, secretaryId);
        return ResponseEntity.ok("Department's secretary changed successfully.");
    }

    @PostMapping("/{departmentId}/change-head")
    public ResponseEntity<String> changeHead(
            @PathVariable Long departmentId,
            @RequestParam Long headId) throws Exception {

        departmentService.changeHead(departmentId, headId);
        return ResponseEntity.ok("Department's head changed successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentUpdateDetailsDto> update(@RequestBody DepartmentUpdateDetailsDto departmentDto) throws Exception {
        var department = departmentService.update(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
