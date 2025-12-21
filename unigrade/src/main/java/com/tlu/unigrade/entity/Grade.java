package com.tlu.unigrade.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grades")
@NoArgsConstructor
@Setter
@Getter
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private long id;

    @Column(name = "continuous", precision = 5, scale = 2)
    private BigDecimal continuousGrade;

    @Column(name = "midterm", precision = 5, scale = 2)
    private BigDecimal midtermGrade;

    @Column(name = "final",precision = 5, scale = 2)
    private BigDecimal finalGrade;
}
