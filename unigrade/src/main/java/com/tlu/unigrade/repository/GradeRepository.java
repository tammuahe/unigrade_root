package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
