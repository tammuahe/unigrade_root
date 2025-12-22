package com.tlu.unigrade.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.entity.Course;
import com.tlu.unigrade.entity.Enrollment;
import com.tlu.unigrade.entity.Student;
import com.tlu.unigrade.enums.EnrollmentStatus;
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

    @Override
    public BigDecimal getCPA() {
        Long studentId = currentUserService.getStudentId();

        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);

        BigDecimal totalWeightedScore = BigDecimal.ZERO;
        int totalCredits = 0;

        for (Enrollment e : enrollments) {
            if (!e.getStatus().isCompleted())
                continue;
            if (e.getGrade() == null || e.getGrade().getFinalGrade() == null)
                continue;

            int credit = e.getSection().getCourse().getCredit();
            BigDecimal score = e.getGrade().getFinalGrade();

            totalWeightedScore = totalWeightedScore.add(score.multiply(BigDecimal.valueOf(credit)));

            totalCredits += credit;
        }

        if (totalCredits == 0) {
            return BigDecimal.ZERO;
        }

        return totalWeightedScore
                .divide(BigDecimal.valueOf(totalCredits), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCompletionPercentage() {
        Long studentId = currentUserService.getStudentId();

        Student student = studentRepository.getReferenceById(studentId);
        List<Course> requiredCourses = student.getProgram().getCourses();

        if (requiredCourses.isEmpty()) {
            return BigDecimal.ZERO;
        }

        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);

        Set<Long> completedCourseIds = enrollments.stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.COMPLETED)
                .map(e -> e.getSection().getCourse().getId())
                .collect(Collectors.toSet());

        long completedRequiredCount = requiredCourses.stream()
                .filter(c -> completedCourseIds.contains(c.getId()))
                .count();

        return BigDecimal.valueOf(completedRequiredCount)
                .multiply(BigDecimal.valueOf(100))
                .divide(
                        BigDecimal.valueOf(requiredCourses.size()),
                        2,
                        RoundingMode.HALF_UP);
    }
}