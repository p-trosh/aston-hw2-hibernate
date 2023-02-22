package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.model.department.DepartmentRequest;
import ru.trosh.astontrainee.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public String departments(Model model) {
        model.addAttribute("departments", departmentService.selectAll());
        return "department/index";
    }

    @GetMapping("view/{id}")
    public String viewDepartment(@PathVariable long id, Model model) {
        model.addAttribute("department", departmentService.selectById(id));
        return "department/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteDepartment(@PathVariable long id, Model model) {
        departmentService.delete(id);
        return "redirect:/department";
    }

    @GetMapping("create")
    public String createDepartment(Model model) {
        return "department/edit";
    }

    @GetMapping("edit/{id}")
    public String editDepartment(@PathVariable long id, Model model) {
        model.addAttribute("department", departmentService.selectById(id));
        return "department/edit";
    }

    @PutMapping("edit/{id}")
    public String editDepartment(
            @PathVariable long id,
            @ModelAttribute final DepartmentRequest department,
            Model model) {
        departmentService.update(id, department);
        return "redirect:/department";
    }

    @PostMapping("create")
    public String createDepartment(@ModelAttribute final DepartmentRequest department, Model model) {
        departmentService.create(department);
        return "redirect:/department";
    }
}
