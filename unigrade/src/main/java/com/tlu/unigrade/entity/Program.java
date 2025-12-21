package com.tlu.unigrade.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "programs")
@NoArgsConstructor
@Getter
@Setter
public class Program {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "programs_courses",
    joinColumns = @JoinColumn(name = "program_id"),
    inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses;

}
