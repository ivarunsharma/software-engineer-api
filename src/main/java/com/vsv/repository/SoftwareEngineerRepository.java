package com.vsv.repository;

import com.vsv.entity.SoftwareEngineer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {

    Optional<SoftwareEngineer> findByEmail(String email);

    boolean existsByEmail(String email);
}
