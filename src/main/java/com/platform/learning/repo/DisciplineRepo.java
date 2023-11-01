package com.platform.learning.repo;

import com.platform.learning.dao.Discipline;
import org.springframework.data.repository.CrudRepository;

public interface DisciplineRepo extends CrudRepository<Discipline,Long> {
}
