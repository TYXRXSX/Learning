package com.platform.learning.repo;

import com.platform.learning.dao.Users;
import com.platform.learning.dao.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<Users,Long> {
    Optional<Users> findByUsername(String name);

    List<Users> findAllByRoles(Role role);
}
