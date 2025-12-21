package com.tlu.unigrade.dto.semester;

import com.tlu.unigrade.enums.SemesterNumber;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SemesterDTO {
    private long id;
    private int year;
    private SemesterNumber semesterNumber;
}
