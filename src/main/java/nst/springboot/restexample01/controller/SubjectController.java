/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author student2
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<SubjectDto> save(@Valid @RequestBody SubjectDto subject) throws Exception {
        SubjectDto subjectDto = subjectService.save(subject);
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        subjectService.delete(id);
        return new ResponseEntity<>("Subject removed!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto) throws Exception {
        var subject = subjectService.update(subjectDto);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
