package com.platform.learning.controller;

import com.platform.learning.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/group")
    public String getTimetables(Model model){
        model.addAttribute("list", groupService.getGroupForCurrentUser().getUsers());
        return "/student/group";
    }
}
