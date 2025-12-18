package com.tlu.unigrade.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private long id;

    private String code;

    private String name;

    private int credit;
    
}
