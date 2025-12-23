package com.tlu.unigrade.service;

import java.math.BigDecimal;
import java.util.List;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.student.StudentDTO;

public interface StudentService {

    List<EnrollmentDTO> findAllEnrollments();

    List<CourseDTO> findAllRequiredCourses();

    List<EnrollmentDTO> searchEnrollment(String keyword);

    StudentDTO getStudent();

    BigDecimal getCPA();

    BigDecimal getCompletionPercentage();

}
