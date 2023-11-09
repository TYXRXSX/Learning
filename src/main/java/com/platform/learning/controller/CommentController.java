package com.platform.learning.controller;

import com.platform.learning.dao.Address;
import com.platform.learning.dao.Comment;
import com.platform.learning.dao.Users;
import com.platform.learning.service.CommentService;
import com.platform.learning.service.DisciplineService;
import com.platform.learning.service.GroupService;
import com.platform.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;

    @PostMapping("/comment/user/accept")
    public String userCommentAccept(@ModelAttribute("Comment")Comment comment,
                                    @RequestParam(name="comm_id", required = false)Long id){
        comment.setCreator(userService.findByUsername(SecurityContextHolder.
                getContext()
                .getAuthentication().getName()));
        comment.setUser(userService.getById(id));
        commentService.saveComment(comment);
        return "redirect:/profile?username="+comment.getUser().getUsername();
    }
    @GetMapping("/comment/delete")
    public String userCommentDelete(@RequestParam(name="id", required = false)Long id){
        String username = commentService.getCommentById(id).getUser().getUsername();
        commentService.deleteById(id);
        return "redirect:/profile?username="+username;
    }
    @GetMapping("/comment/edit/{username}/{id}")
    public String userCommentEdit(Model model, @PathVariable String username,
                                  @PathVariable Long id){
        model.addAttribute("Group",groupService.getGroupForCurrentUser());
        model.addAttribute("User",userService.findByUsername(username));
        model.addAttribute("list", commentService.getCommentsForUser(userService.findByUsername(username).getId()));
        model.addAttribute("Comment", commentService.getCommentById(id));
        return "profile";
    }
    ///////
    @PostMapping("/comment/discipline/accept")
    public String disciplineCommentAccept(@ModelAttribute("Comment")Comment comment,
                                    @RequestParam(name="comm_id", required = false)Long id){
        comment.setCreator(userService.findByUsername(SecurityContextHolder.
                getContext()
                .getAuthentication().getName()));
        comment.setDiscipline(disciplineService.getDiscipline(id));
        commentService.saveComment(comment);
        return "redirect:/discipline?id="+comment.getDiscipline().getId();

    }
    @GetMapping("/comment/edit/discipline/{discipline}/{id}")
    public String disciplineCommentEdit(Model model, @PathVariable Long discipline,
                                  @PathVariable Long id){
        model.addAttribute("Discipline", disciplineService.getDiscipline(discipline));
        model.addAttribute("list", commentService.getCommentsForDiscipline(discipline));
        model.addAttribute("Comment", commentService.getCommentById(id));
        return "/student/discipline/discipline";
    }
    @GetMapping("/comment/delete/discipline/{discipline}/{id}")
    public String userCommentDelete(@PathVariable Long discipline,
                                    @PathVariable Long id){
        commentService.deleteById(id);
        return "redirect:/discipline?id="+discipline;
    }
}
