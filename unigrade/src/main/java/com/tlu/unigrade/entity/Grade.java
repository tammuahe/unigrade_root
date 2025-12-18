package com.tlu.unigrade.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades")
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "enrollment_id", unique = true)
    private Enrollment enrollment;

    @Column(name = "continuous", precision = 5, scale = 2)
    private BigDecimal continuousGrade;

    @Column(name = "midterm", precision = 5, scale = 2)
    private BigDecimal midtermGrade;

    @Column(name = "final",precision = 5, scale = 2)
    private BigDecimal finalGrade;
}
