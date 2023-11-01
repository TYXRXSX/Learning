package com.platform.learning.service;

import com.platform.learning.dao.StudentsGroup;
import com.platform.learning.dao.Timetable;
import com.platform.learning.dao.Users;
import com.platform.learning.repo.GroupRepo;
import com.platform.learning.repo.TimetableRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepo timetableRepo;
    private final GroupRepo groupRepo;
    private final UserService userService;
    public Iterable<Timetable> getTimetables() {
        return timetableRepo.findAll();
    }
    public Iterable<Timetable> getTimetablesByGroup() {
        Users users = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Users> usersList = new ArrayList<Users>();
        usersList.add(users);
        StudentsGroup studentsGroup = groupRepo.findByUsers(users);
        return timetableRepo.findAllByStudentsGroup(studentsGroup);
    }

    public Timetable getTimetable(long id) {
        return timetableRepo.findById(id).get();
    }

    public void save(Timetable timetable) {
        timetableRepo.save(timetable);
    }
}
