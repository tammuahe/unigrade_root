package com.tlu.unigrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
