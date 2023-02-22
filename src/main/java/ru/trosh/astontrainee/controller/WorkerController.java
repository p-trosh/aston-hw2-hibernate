package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.dao.DepartmentDAO;
import ru.trosh.astontrainee.dao.SpecialityDAO;
import ru.trosh.astontrainee.dao.WorkerDAO;
import ru.trosh.astontrainee.domain.Department;
import ru.trosh.astontrainee.domain.Speciality;
import ru.trosh.astontrainee.domain.Worker;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerDAO dao;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private SpecialityDAO specialityDAO;

    @GetMapping()
    public String workers(Model model) {
        model.addAttribute("workers", dao.selectAll());
        return "worker/index";
    }

    @GetMapping("view/{id}")
    public String worker(@PathVariable long id, Model model) {
        model.addAttribute("worker", dao.selectById(id));
        return "worker/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteWorker(@PathVariable long id, Model model) {
        dao.delete(id);
        return "redirect:/worker";
    }

    @GetMapping("create")
    public String createWorker(Model model) {
        model.addAttribute("departments", departmentDAO.selectAll());
        model.addAttribute("specialities", specialityDAO.selectAll());
        return "worker/edit";
    }

    @GetMapping("edit/{id}")
    public String editWorker(@PathVariable long id, Model model) {
        model.addAttribute("departments", departmentDAO.selectAll());
        model.addAttribute("specialities", specialityDAO.selectAll());
        model.addAttribute("worker", dao.selectById(id));
        return "worker/edit";
    }

    @PutMapping("edit")
    public String editWorker(@ModelAttribute final WorkerForm workerForm, Model model) {
        dao.update(Worker.builder()
                .id(workerForm.getId())
                .firstName(workerForm.getFirstName())
                .lastName(workerForm.getLastName())
                .department(Department.builder()
                        .id(workerForm.getDepartmentId())
                        .build())
                .speciality(Speciality.builder()
                        .id(workerForm.getSpecialityId())
                        .build())
                .build());
        return "redirect:/worker";
    }

    @PostMapping("create")
    public String createWorker(@ModelAttribute final WorkerForm workerForm, Model model) {
        dao.create(Worker.builder()
                .firstName(workerForm.getFirstName())
                .lastName(workerForm.getLastName())
                .department(Department.builder()
                        .id(workerForm.getDepartmentId())
                        .build())
                .speciality(Speciality.builder()
                        .id(workerForm.getSpecialityId())
                        .build())
                .build());
        return "redirect:/worker";
    }

    public static class WorkerForm {
        private Long id;
        private String firstName;
        private String lastName;
        private Long departmentId;
        private Long specialityId;

        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public Long getSpecialityId() {
            return specialityId;
        }
    }





}
