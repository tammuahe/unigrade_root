package com.tlu.unigrade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByCourseIdAndSemesterIdAndLecturerId(Long courseId, Long semesterId, Long lecturerId);

}
