package com.platform.learning.dao;

import com.platform.learning.dao.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Attendance> attendances;
    private String phone;
    @OneToOne
    private Address address = new Address();
    private String username;
    private String firstname;
    private String middlename;
    private String surname;
    private String passwordHash;
    private boolean active = true;

}
