package com.platform.learning.controller;

import com.platform.learning.dao.Comment;
import com.platform.learning.service.CommentService;
import com.platform.learning.service.DisciplineService;
import com.platform.learning.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;
    private final CommentService commentService;

    @GetMapping("/discipline")
    public String getDiscipline(Model model, @RequestParam(name="id", required = false)Long id){
        model.addAttribute("Discipline", disciplineService.getDiscipline(id));
        model.addAttribute("list", commentService.getCommentsForDiscipline(id));
        model.addAttribute("Comment", new Comment());
        return "/student/discipline/discipline";
    }
}
