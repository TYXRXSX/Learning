package com.platform.learning.repo;

import com.platform.learning.dao.StudentsGroup;
import com.platform.learning.dao.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepo extends CrudRepository<StudentsGroup,Long> {
    StudentsGroup findByUsers(Users users);
}
