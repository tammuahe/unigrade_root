package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
