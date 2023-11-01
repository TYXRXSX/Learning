package com.platform.learning.controller;

import com.platform.learning.dao.Address;
import com.platform.learning.dao.Comment;
import com.platform.learning.dao.Users;
import com.platform.learning.service.CommentService;
import com.platform.learning.service.GroupService;
import com.platform.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final GroupService groupService;

    @PostMapping("/comment/user/accept")
    public String userCommentAccept(@ModelAttribute("Comment")Comment comment,
                                    @RequestParam(name="comm_id", required = false)Long id){
        comment.setUser(userService.getById(id));
        commentService.saveComment(comment);
        return "redirect:/profile?username="+comment.getUser().getUsername();
    }
    @PostMapping("/comment/discipline/accept")
    public String userDisciplineAccept(@ModelAttribute("Comment")Comment comment,
                                       @RequestParam(name="id", required = false)Long id){
        commentService.saveComment(comment);
        return "redirect:/profile?id="+comment.getUser().getId();
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
}
