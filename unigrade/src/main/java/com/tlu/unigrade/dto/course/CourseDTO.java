package com.tlu.unigrade.dto.course;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String code;
    private String name;
    private int credit;
}
