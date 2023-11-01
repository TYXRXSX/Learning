package com.platform.learning.controller;

import com.platform.learning.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/timetables")
    public String getTimetables(Model model){
        model.addAttribute("list", timetableService.getTimetablesByGroup());
        return "/student/timetable";
    }
}
