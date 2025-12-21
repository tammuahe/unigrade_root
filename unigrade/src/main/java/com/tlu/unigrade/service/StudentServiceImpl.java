package com.tlu.unigrade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.entity.Student;
import com.tlu.unigrade.mapper.CourseMapper;
import com.tlu.unigrade.mapper.EnrollmentMapper;
import com.tlu.unigrade.mapper.StudentMapper;
import com.tlu.unigrade.repository.EnrollmentRepository;
import com.tlu.unigrade.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CurrentUserService currentUserService;
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    @Override
    public List<EnrollmentDTO> findAllEnrollments() {
        return enrollmentRepository.findAllByStudentId(currentUserService.getStudentId()).stream()
                .map(enrollment -> enrollmentMapper.toDto(enrollment)).toList();
    }

    @Override
    public List<CourseDTO> findAllRequiredCourses() {
        Student student = studentRepository.getReferenceById(currentUserService.getStudentId());

        return student.getProgram().getCourses().stream().map(course -> courseMapper.toDto(course)).toList();
    }

    @Override
    public StudentDTO getStudent() {
        return studentMapper.toDto(studentRepository.findById(currentUserService.getStudentId()).orElseThrow());
    }

    @Override
    public List<EnrollmentDTO> searchEnrollment(String keyword) {
        return enrollmentRepository.searchByCourseNameOrCode(currentUserService.getStudentId(), keyword).stream()
                .map(enrollment -> enrollmentMapper.toDto(enrollment)).toList();
    }
}