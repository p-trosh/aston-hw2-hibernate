package ru.trosh.astontrainee.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.dao.DepartmentDAO;
import ru.trosh.astontrainee.dao.TaskDAO;
import ru.trosh.astontrainee.model.Department;
import ru.trosh.astontrainee.model.Task;

import java.time.LocalDate;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskDAO dao;

    @Autowired
    private DepartmentDAO departmentDAO;

    @GetMapping()
    public String tasks(Model model) {
        model.addAttribute("tasks", dao.selectAll());
        return "task/index";
    }

    @GetMapping("view/{id}")
    public String task(@PathVariable long id, Model model) {
        model.addAttribute("task", dao.selectById(id));
        return "task/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteTask(@PathVariable long id, Model model) {
        dao.delete(id);
        return "redirect:/task";
    }

    @GetMapping("create")
    public String createTask(Model model) {
        model.addAttribute("departments", departmentDAO.selectAll());
        return "task/edit";
    }

    @GetMapping("edit/{id}")
    public String editTask(@PathVariable long id, Model model) {
        model.addAttribute("departments", departmentDAO.selectAll());
        model.addAttribute("task", dao.selectById(id));
        return "task/edit";
    }

    @PutMapping("edit")
    public String editTask(@ModelAttribute final TaskForm task, Model model) {
        dao.update(Task.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .department(Department.builder()
                        .id(task.getDepartmentId())
                        .build())
                .build());
        return "redirect:/task";
    }

    @PostMapping("create")
    public String createTask(@ModelAttribute final TaskForm task, Model model) {
        dao.create(Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .department(Department.builder()
                        .id(task.getDepartmentId())
                        .build())
                .build());
        return "redirect:/task";
    }

    @Getter
    @Setter
    public static class TaskForm {
        private Long id;
        private String title;
        private String description;
        private Long departmentId;
    }
}
