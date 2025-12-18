package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long>{

}
