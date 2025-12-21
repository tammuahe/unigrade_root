package com.tlu.unigrade.dto.enrollment;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.grade.GradeDTO;
import com.tlu.unigrade.dto.lecturer.LecturerDTO;
import com.tlu.unigrade.dto.semester.SemesterDTO;
import com.tlu.unigrade.enums.EnrollmentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnrollmentDTO {
    private Long id;
    private CourseDTO course;
    private SemesterDTO semester;
    private LecturerDTO lecturer;
    private GradeDTO grade;
    private EnrollmentStatus status;
}
