package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.dao.DepartmentDAO;
import ru.trosh.astontrainee.domain.Department;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentDAO dao;

    @GetMapping()
    public String departments(Model model) {
        model.addAttribute("departments", dao.selectAll());
        return "department/index";
    }

    @GetMapping("view/{id}")
    public String viewDepartment(@PathVariable long id, Model model) {
        model.addAttribute("department", dao.selectById(id));
        return "department/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteDepartment(@PathVariable long id, Model model) {
        dao.delete(id);
        return "redirect:/department";
    }

    @GetMapping("create")
    public String createDepartment(Model model) {
        return "department/edit";
    }

    @GetMapping("edit/{id}")
    public String editDepartment(@PathVariable long id, Model model) {
        model.addAttribute("department", dao.selectById(id));
        return "department/edit";
    }

    @PutMapping("edit")
    public String editDepartment(@ModelAttribute final Department department, Model model) {
        dao.update(department);
        return "redirect:/department";
    }

    @PostMapping("create")
    public String createDepartment(@ModelAttribute final Department department, Model model) {
        dao.create(department);
        return "redirect:/department";
    }
}
