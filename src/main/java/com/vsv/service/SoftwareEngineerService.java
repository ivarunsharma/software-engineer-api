package com.vsv.service;

import com.vsv.dto.SoftwareEngineerRequest;
import com.vsv.dto.SoftwareEngineerResponse;
import com.vsv.entity.SoftwareEngineer;
import com.vsv.exception.ResourceNotFoundException;
import com.vsv.repository.SoftwareEngineerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository repository;

    public List<SoftwareEngineerResponse> getAllSoftwareEngineers() {
        log.info("Fetching all software engineers");
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public SoftwareEngineerResponse getSoftwareEngineerById(Integer id) {
        log.info("Fetching software engineer with id: {}", id);
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found with id: " + id));
    }

    @Transactional
    public SoftwareEngineerResponse createSoftwareEngineer(SoftwareEngineerRequest request) {
        log.info("Creating software engineer: {}", request.name());
        SoftwareEngineer engineer = new SoftwareEngineer();
        engineer.setName(request.name());
        engineer.setTechStack(request.techStack());
        engineer.setEmail(request.email());
        return toResponse(repository.save(engineer));
    }

    @Transactional
    public SoftwareEngineerResponse updateSoftwareEngineer(Integer id, SoftwareEngineerRequest request) {
        log.info("Updating software engineer with id: {}", id);
        SoftwareEngineer engineer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software engineer not found with id: " + id));
        engineer.setName(request.name());
        engineer.setTechStack(request.techStack());
        engineer.setEmail(request.email());
        return toResponse(repository.save(engineer));
    }

    @Transactional
    public void deleteSoftwareEngineer(Integer id) {
        log.info("Deleting software engineer with id: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Software engineer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private SoftwareEngineerResponse toResponse(SoftwareEngineer engineer) {
        return new SoftwareEngineerResponse(
                engineer.getId(),
                engineer.getName(),
                engineer.getTechStack(),
                engineer.getEmail()
        );
    }
}
