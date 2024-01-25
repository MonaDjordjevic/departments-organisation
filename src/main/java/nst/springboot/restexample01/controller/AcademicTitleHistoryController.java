package nst.springboot.restexample01.controller;

import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.service.AcademicTitleHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academic-title-history")
public class AcademicTitleHistoryController {

    private final AcademicTitleHistoryService academicTitleHistoryService;

    @GetMapping
    public ResponseEntity<List<AcademicTitleHistoryDto>> getAcademicTitleHistoriesOfAllMembers() {
        return ResponseEntity.ok(academicTitleHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicTitleHistoryDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(academicTitleHistoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        academicTitleHistoryService.delete(id);
        return new ResponseEntity<>("Academic history removed!", HttpStatus.OK);
    }
}
