package com.platform.learning.service;

import com.platform.learning.dao.StudentsGroup;
import com.platform.learning.dao.Users;
import com.platform.learning.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepo;
    private final UserService userService;

    public List<StudentsGroup> getGroups(){
       return (List<StudentsGroup>) groupRepo.findAll();
    }

    public StudentsGroup getGroup(Long id){
        return groupRepo.findById(id).get();
    }

    public StudentsGroup getGroupForCurrentUser(){
        Users users = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return groupRepo.findByUsers(users);
    }

    public void save(StudentsGroup group) {
        if (group.getId()!=null) {
            StudentsGroup group1 = groupRepo.findById(group.getId()).get();
            group.setUsers(group1.getUsers());
        }
        groupRepo.save(group);
    }

    public void delete(Long id) {
        groupRepo.deleteById(id);
    }
}
