package com.tlu.unigrade.dto.grade;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GradeDTO {
    private Long id;
    private BigDecimal continuousGrade;
    private BigDecimal midtermGrade;
    private BigDecimal finalGrade;
}
