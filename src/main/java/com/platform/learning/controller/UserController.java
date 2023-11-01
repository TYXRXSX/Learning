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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GroupService groupService;
    private final CommentService commentService;
    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("Users",new Users());
        return "registration";
    }
    @PostMapping("/register/accept")
    public String userRegister(@ModelAttribute("User") Users user, @ModelAttribute("Address") Address address){
        user.setAddress(address);
        userService.registration(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String getUsersProfilePage(Model model, @RequestParam(name="username", required = false)String username){
        model.addAttribute("Group",groupService.getGroupForCurrentUser());
        model.addAttribute("User",userService.findByUsername(username));
        model.addAttribute("list", commentService.getCommentsForUser(userService.findByUsername(username).getId()));
        model.addAttribute("Comment", new Comment());
        return "profile";
    }
}
