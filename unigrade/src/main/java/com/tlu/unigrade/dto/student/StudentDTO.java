package com.tlu.unigrade.dto.student;

import java.time.LocalDate;

import com.tlu.unigrade.dto.program.ProgramDTO;
import com.tlu.unigrade.enums.StudentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter 
public class StudentDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private ProgramDTO program;
    private StudentStatus status;
}
