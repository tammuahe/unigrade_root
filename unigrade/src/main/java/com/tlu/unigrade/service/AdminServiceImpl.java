package com.tlu.unigrade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.grade.GradeDTO;
import com.tlu.unigrade.entity.Student;
import com.tlu.unigrade.entity.Course;
import com.tlu.unigrade.entity.Enrollment;
import com.tlu.unigrade.entity.Grade;
import com.tlu.unigrade.mapper.StudentMapper;
import com.tlu.unigrade.mapper.CourseMapper;
import com.tlu.unigrade.mapper.EnrollmentMapper;
import com.tlu.unigrade.mapper.GradeMapper;
import com.tlu.unigrade.mapper.ProgramMapper;
import com.tlu.unigrade.repository.StudentRepository;
import com.tlu.unigrade.repository.CourseRepository;
import com.tlu.unigrade.repository.EnrollmentRepository;
import com.tlu.unigrade.repository.GradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;

    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final EnrollmentMapper enrollmentMapper;
    private final GradeMapper gradeMapper;
    private final ProgramMapper programMapper;

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = studentMapper.toEntity(dto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setProgram(programMapper.toEntity(dto.getProgram()));
        student.setStatus(dto.getStatus());

        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public CourseDTO createCourse(CourseDTO dto) {
        Course course = courseMapper.toEntity(dto);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found with id " + id));

        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setCredit(dto.getCredit());

        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO dto) {
        Enrollment enrollment = enrollmentMapper.toEntity(dto);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(enrollment);
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enrollment not found with id " + id));
        return enrollmentMapper.toDto(enrollment);
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO dto) {
        Enrollment enrollment = enrollmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enrollment not found with id " + id));

        enrollment.setStatus(dto.getStatus());

        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(enrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found with id " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    @Override
    public GradeDTO createGrade(GradeDTO dto) {
        Grade grade = gradeMapper.toEntity(dto);
        grade = gradeRepository.save(grade);
        return gradeMapper.toDto(grade);
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
        return gradeMapper.toDto(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO dto) {
        Grade grade = gradeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));

        grade.setContinuousGrade(dto.getContinuousGrade());
        grade.setMidtermGrade(dto.getMidtermGrade());
        grade.setFinalGrade(dto.getFinalGrade());

        grade = gradeRepository.save(grade);
        return gradeMapper.toDto(grade);
    }

    @Override
    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new RuntimeException("Grade not found with id " + id);
        }
        gradeRepository.deleteById(id);
    }
}
