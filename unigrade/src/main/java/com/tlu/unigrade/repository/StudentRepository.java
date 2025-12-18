package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
