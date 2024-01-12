package nst.springboot.restexample01.controller;

import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.SecretaryHistoryDto;
import nst.springboot.restexample01.service.SecretaryHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/secretary-history")
public class SecretaryHistoryController {

    private final SecretaryHistoryService secretaryHistoryService;

    @GetMapping
    public ResponseEntity<List<SecretaryHistoryDto>> getAll() {
        return ResponseEntity.ok(secretaryHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryHistoryDto> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(secretaryHistoryService.findById(id));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<SecretaryHistoryDto>> getSecretaryHistoryOfDepartment(@PathVariable Long departmentId) {
        List<SecretaryHistoryDto> secretaryHistoryDtoList = secretaryHistoryService.getSecretaryHistoryOfDepartment(departmentId);
        return new ResponseEntity<>(secretaryHistoryDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        secretaryHistoryService.delete(id);
        return new ResponseEntity<>("Secretary history removed!", HttpStatus.OK);
    }
}
