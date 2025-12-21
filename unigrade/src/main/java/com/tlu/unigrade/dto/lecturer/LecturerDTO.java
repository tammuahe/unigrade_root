package com.tlu.unigrade.dto.lecturer;

import com.tlu.unigrade.enums.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LecturerDTO {
    private long id;
    private String name;
    private Department department;
}
