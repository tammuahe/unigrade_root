package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
