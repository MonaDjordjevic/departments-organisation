package nst.springboot.restexample01.controller;

import lombok.RequiredArgsConstructor;
import nst.springboot.restexample01.dto.HeadHistoryDto;
import nst.springboot.restexample01.service.HeadHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/head-history")
public class HeadHistoryController {

    private final HeadHistoryService headHistoryService;

    @GetMapping
    public ResponseEntity<List<HeadHistoryDto>> getAll() {
        return ResponseEntity.ok(headHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeadHistoryDto> findById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(headHistoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        headHistoryService.delete(id);
        return new ResponseEntity<>("Head history removed!", HttpStatus.OK);
    }
}
