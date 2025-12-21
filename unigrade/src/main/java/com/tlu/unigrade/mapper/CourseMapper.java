package com.tlu.unigrade.mapper;

import org.springframework.stereotype.Component;

import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.entity.Course;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    public CourseDTO toDto(Course course) {
        if (course == null)
            return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setCredit(course.getCredit());
        return dto;
    }

    public Course toEntity(CourseDTO dto) {
        if (dto == null)
            return null;

        Course course = new Course();
        course.setId(dto.getId());
        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setCredit(dto.getCredit());
        return course;
    }
}
