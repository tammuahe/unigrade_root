package com.tlu.unigrade.mapper;

import org.springframework.stereotype.Component;

import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.entity.Enrollment;
import com.tlu.unigrade.entity.Section;
import com.tlu.unigrade.repository.SectionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final CourseMapper courseMapper;
    private final GradeMapper gradeMapper;
    private final SemesterMapper semesterMapper;
    private final LecturerMapper lecturerMapper;

    private final SectionRepository sectionRepository; // Inject repository here

    public EnrollmentDTO toDto(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setCourse(courseMapper.toDto(enrollment.getSection().getCourse()));
        dto.setSemester(semesterMapper.toDto(enrollment.getSection().getSemester()));
        dto.setLecturer(lecturerMapper.toDto(enrollment.getSection().getLecturer()));
        dto.setGrade(gradeMapper.toDto(enrollment.getGrade()));
        dto.setStatus(enrollment.getStatus());
        return dto;
    }

    public Enrollment toEntity(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(dto.getId());

        // Fetch Section entity by matching courseId, semesterId, lecturerId
        Long courseId = dto.getCourse().getId();
        Long semesterId = dto.getSemester().getId();
        Long lecturerId = dto.getLecturer().getId();

        Section section = sectionRepository.findByCourseIdAndSemesterIdAndLecturerId(courseId, semesterId, lecturerId)
            .orElseThrow(() -> new IllegalArgumentException("Section not found for given course, semester, and lecturer"));

        enrollment.setSection(section);
        enrollment.setGrade(gradeMapper.toEntity(dto.getGrade()));
        enrollment.setStatus(dto.getStatus());

        return enrollment;
    }
}
