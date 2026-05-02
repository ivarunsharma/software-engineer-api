package com.vsv.controller;

import com.vsv.dto.SoftwareEngineerRequest;
import com.vsv.dto.SoftwareEngineerResponse;
import com.vsv.service.SoftwareEngineerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/software-engineers")
@RequiredArgsConstructor
public class SoftwareEngineerController {

    private final SoftwareEngineerService service;

    @GetMapping
    public ResponseEntity<List<SoftwareEngineerResponse>> getAll() {
        return ResponseEntity.ok(service.getAllSoftwareEngineers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoftwareEngineerResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getSoftwareEngineerById(id));
    }

    @PostMapping
    public ResponseEntity<SoftwareEngineerResponse> create(
            @Valid @RequestBody SoftwareEngineerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createSoftwareEngineer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoftwareEngineerResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody SoftwareEngineerRequest request) {
        return ResponseEntity.ok(service.updateSoftwareEngineer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteSoftwareEngineer(id);
        return ResponseEntity.noContent().build();
    }
}
