package com.tlu.unigrade.dto.section;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.semester.SemesterDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SectionDTO {
    private Long id;
    private CourseDTO course;
    private SemesterDTO semester;
    
}
