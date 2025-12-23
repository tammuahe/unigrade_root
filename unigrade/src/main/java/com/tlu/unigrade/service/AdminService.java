package com.tlu.unigrade.service;

import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.grade.GradeDTO;

import java.util.List;

public interface AdminService {

    // Student CRUD
    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);

    CourseDTO createCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(Long id);

    List<CourseDTO> getAllCourses();

    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    void deleteCourse(Long id);

    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO getEnrollmentById(Long id);

    List<EnrollmentDTO> getAllEnrollments();

    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Long id);

    GradeDTO createGrade(GradeDTO gradeDTO);

    GradeDTO getGradeById(Long id);

    List<GradeDTO> getAllGrades();

    GradeDTO updateGrade(Long id, GradeDTO gradeDTO);

    void deleteGrade(Long id);
}
