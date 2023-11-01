package com.platform.learning.dao;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private StudentsGroup studentsGroup;

    @OneToMany
    private List<Discipline> disciplines;

    private LocalDate localDate;
}
