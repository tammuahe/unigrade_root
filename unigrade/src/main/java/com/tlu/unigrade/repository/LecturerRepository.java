package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}
