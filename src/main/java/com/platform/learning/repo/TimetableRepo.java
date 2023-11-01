package com.platform.learning.repo;

import com.platform.learning.dao.StudentsGroup;
import com.platform.learning.dao.Timetable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimetableRepo extends CrudRepository<Timetable,Long> {
    List<Timetable> findAllByStudentsGroup(StudentsGroup studentsGroup);
}
