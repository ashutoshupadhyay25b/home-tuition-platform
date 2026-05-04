package com.tuition.app.controller;

import com.tuition.app.dto.RequirementDTO;
import com.tuition.app.entity.Requirement;
import com.tuition.app.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requirements")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;

    @PostMapping("/{studentId}")
    public ResponseEntity<RequirementDTO> create(@PathVariable Long studentId, @RequestBody Requirement requirement) {
        return ResponseEntity.ok(requirementService.createRequirement(studentId, requirement));
    }

    @GetMapping("/active")
    public ResponseEntity<List<RequirementDTO>> getActive() {
        return ResponseEntity.ok(requirementService.getAllActiveRequirements());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RequirementDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(requirementService.getRequirementsByStudent(studentId));
    }
}
