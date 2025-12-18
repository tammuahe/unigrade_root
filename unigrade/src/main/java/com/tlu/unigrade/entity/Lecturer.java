package com.tlu.unigrade.entity;

import com.tlu.unigrade.enums.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "lecturers")
@NoArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue
    @Column(name = "lecturer_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Department department;
}
