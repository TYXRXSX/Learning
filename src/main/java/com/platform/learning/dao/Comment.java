package com.platform.learning.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;
    @OneToOne
    private Timetable timetable;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Discipline discipline;
}
