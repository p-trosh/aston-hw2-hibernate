package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.model.worker.WorkerRequest;
import ru.trosh.astontrainee.service.DepartmentService;
import ru.trosh.astontrainee.service.SpecialityService;
import ru.trosh.astontrainee.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SpecialityService specialityService;

    @GetMapping()
    public String workers(Model model) {
        model.addAttribute("workers", workerService.selectAll());
        return "worker/index";
    }

    @GetMapping("view/{id}")
    public String worker(@PathVariable long id, Model model) {
        model.addAttribute("worker", workerService.selectById(id));
        return "worker/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteWorker(@PathVariable long id, Model model) {
        workerService.delete(id);
        return "redirect:/worker";
    }

    @GetMapping("create")
    public String createWorker(Model model) {
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("specialities", specialityService.selectAll());
        return "worker/edit";
    }

    @GetMapping("edit/{id}")
    public String editWorker(@PathVariable long id, Model model) {
        model.addAttribute("departments", departmentService.selectAll());
        model.addAttribute("specialities", specialityService.selectAll());
        model.addAttribute("worker", workerService.selectById(id));
        return "worker/edit";
    }

    @PutMapping("edit/{id}")
    public String editWorker(@PathVariable long id, @ModelAttribute final WorkerRequest workerRequest, Model model) {
        workerService.update(id, workerRequest);
        return "redirect:/worker";
    }

    @PostMapping("create")
    public String createWorker(@ModelAttribute final WorkerRequest workerRequest, Model model) {
        workerService.create(workerRequest);
        return "redirect:/worker";
    }
}
