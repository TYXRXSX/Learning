package com.platform.learning.dao;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
