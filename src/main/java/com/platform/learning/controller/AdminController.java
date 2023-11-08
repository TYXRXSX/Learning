package com.platform.learning.controller;

import com.platform.learning.dao.*;
import com.platform.learning.dao.enums.Role;
import com.platform.learning.repo.AddressRepo;
import com.platform.learning.service.DisciplineService;
import com.platform.learning.service.GroupService;
import com.platform.learning.service.TimetableService;
import com.platform.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final GroupService groupService;
    private final UserService userService;
    private final AddressRepo addressRepo;
    private final DisciplineService disciplineService;
    private final TimetableService timetableService;

    @GetMapping("/admin")
    public String getAdminPanel(Model model){
        return "admin/panel";
    }
    @GetMapping("/admin/discipline")
    public String getDisciplineList(Model model){
        model.addAttribute("list",disciplineService.getDisciplines());
        return "admin/disciplines/list";
    }
    @GetMapping("/admin/discipline/edit")
    public String editDiscipline(Model model, @RequestParam(name="id", required = false)Long id){
        if (null==id){
            model.addAttribute("Discipline", new Discipline());
        }
        else {
            model.addAttribute("Discipline", disciplineService.getDiscipline(id));
        }
        return "admin/disciplines/add";
    }
    @PostMapping("/admin/discipline/edit/accept")
    public String editDisciplineAccept(Model model, @ModelAttribute("Discipline")Discipline discipline){
        disciplineService.save(discipline);
        return "redirect:/admin/discipline";
    }
    @GetMapping("/admin/discipline/delete")
    public String deleteDiscipline(Model model, @RequestParam(name="id", required = false)Long id){
        disciplineService.delete(id);
        return "redirect:/admin/discipline";
    }
    @GetMapping("/admin/group")
    public String getGroupList(Model model){
        model.addAttribute("list",groupService.getGroups());
        return "admin/groupcrud/group";
    }
    @GetMapping("/admin/group/edit")
    public String editGroup(Model model, @RequestParam(name="id", required = false)Long id){
        if (null==id){
            model.addAttribute("group", new StudentsGroup());
        }
        else {
            model.addAttribute("group", groupService.getGroup(id));
        }
        return "admin/groupcrud/add";
    }

    @GetMapping("/admin/group/delete")
    public String deleteGroup(Model model, @RequestParam(name="id", required = false)Long id){
        groupService.delete(id);
        return "redirect:/admin/group";
    }

    @PostMapping("/admin/group/edit/accept")
    public String getGroupList(Model model, @ModelAttribute("group")StudentsGroup group){
        groupService.save(group);
        return "redirect:/admin/group";
    }
    @GetMapping("/admin/group/{groupId}/add-student")
    public String editGroupStudent(Model model, @PathVariable Long groupId){
        model.addAttribute("list",userService.findAllByRole(Role.ADMIN));
        model.addAttribute("groupId", groupId);
        return "admin/groupcrud/users";
    }
    @GetMapping("/admin/group/{groudId}/add-student/{studentId}")
    public String editGroupStudentAccept(Model model,
                                         @PathVariable Long groudId,
                                         @PathVariable Long studentId){
        Users users =userService.getById(studentId);
        StudentsGroup group = groupService.getGroup(groudId);
        group.getUsers().add(users);
        groupService.save(group);
        return "redirect:/admin/group/edit?id="+groudId;
    }
    @GetMapping("/admin/group/{groudId}/delete-student/{studentId}")
    public String deleteGroupStudentAccept(Model model,
                                         @PathVariable Long groudId,
                                         @PathVariable Long studentId){
        Users users =userService.getById(studentId);
        StudentsGroup group = groupService.getGroup(groudId);
        group.getUsers().remove(users);
        groupService.save(group);
        return "redirect:/admin/group/edit?id="+groudId;
    }
    @GetMapping("/admin/users")
    public String getUser(Model model){
        model.addAttribute("users", userService.getAll());
        return "/admin/users/list";
    }
    @GetMapping("/admin/users/edit")
    public String editUser(Model model, @RequestParam(name="id", required = true)long id){
        Users user = userService.getById(id);
        Address address = addressRepo.findById(user.getAddress().getId()).get();
        model.addAttribute("User", user);
        model.addAttribute("Roles", Role.values());
        model.addAttribute("Address", address);
        return "/admin/users/add";
    }
    @GetMapping("/admin/users/delete")
    public String deleteUser(Model model, @RequestParam(name="id", required = true)long id){
        userService.delete(id);
        return "redirect:/admin/users";
    }
    //, @RequestParam(name="privillege_id", required = true)Long id
    @PostMapping("/admin/users/edit/accept")
    public String editUserAccept(Model model, @ModelAttribute("User") Users user, @ModelAttribute("Address") Address address){
        Address address1 = addressRepo.save(address);
        user.setAddress(address1);
        userService.edit(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/timetables")
    public String getTimetables(Model model){
        model.addAttribute("list", timetableService.getTimetables());
        return "/admin/timetable/list";
    }
    @GetMapping("/admin/timetables/edit")
    public String editTimetables(Model model,@RequestParam(name="id", required = false)Long id){
        if (null==id){
            model.addAttribute("Timetable", new Timetable());
        }
        else {
            model.addAttribute("Timetable", timetableService.getTimetable(id));
        }
        model.addAttribute("Groups",groupService.getGroups());
        return "/admin/timetable/add";
    }
    @PostMapping("/admin/timetables/edit/accept")
    public String editTimetablesAccept(Model model,@ModelAttribute("group")Timetable timetable){
        if (timetable.getId() != null) {
            timetable.setDisciplines(timetableService.getTimetable(timetable.getId()).getDisciplines());
        }
        timetableService.save(timetable);
        return "redirect:/admin/timetables";
    }
    @GetMapping("/admin/timetables/{timetableId}/add-discipline")
    public String editTimetablesDiscipline(Model model, @PathVariable Long timetableId){
        model.addAttribute("list",disciplineService.getDisciplines());
        model.addAttribute("timetableId", timetableId);
        return "admin/timetable/disciplines";
    }
    @GetMapping("/admin/timetables/{timetableId}/add-discipline/{disciplineId}")
    public String editTimetableDisciplineAccept(Model model,
                                                @PathVariable Long timetableId,
                                         @PathVariable Long disciplineId){
        Discipline discipline = disciplineService.getDiscipline(disciplineId);
        Timetable timetable = timetableService.getTimetable(timetableId);
        timetable.getDisciplines().add(discipline);
        timetableService.save(timetable);
        return "redirect:/admin/timetables/edit?id="+timetableId;
    }
    @GetMapping("/admin/timetables/{timetableId}/delete-discipline/{disciplineId}")
    public String deleteTimetableDisciplineAccept(Model model,
                                           @PathVariable Long timetableId,
                                           @PathVariable Long disciplineId){
        Discipline discipline = disciplineService.getDiscipline(disciplineId);
        Timetable timetable = timetableService.getTimetable(timetableId);
        timetable.getDisciplines().remove(discipline);
        timetableService.save(timetable);
        return "redirect:/admin/timetables/edit?id="+timetableId;
    }

    @GetMapping("/admin/timetables/delete")
    public String deleteTimetable(Model model, @RequestParam(name="id", required = true)long id){
        timetableService.delete(id);
        return "redirect:/admin/timetables";
    }
}
