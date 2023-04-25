package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.model.speciality.SpecialityRequest;
import ru.trosh.astontrainee.service.SpecialityService;

@Controller
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GetMapping()
    public String specialities(Model model) {
        model.addAttribute("specialities", specialityService.selectAll());
        return "speciality/index";
    }

    @GetMapping("view/{id}")
    public String viewSpeciality(@PathVariable long id, Model model) {
        model.addAttribute("speciality", specialityService.selectById(id));
        return "speciality/view";
    }

    @DeleteMapping("delete/{id}")
    public String deleteSpeciality(@PathVariable long id) {
        specialityService.delete(id);
        return "redirect:/speciality";
    }

    @GetMapping("create")
    public String createSpeciality(Model model) {
        return "speciality/edit";
    }

    @GetMapping("edit/{id}")
    public String editSpeciality(@PathVariable long id, Model model) {
        model.addAttribute("speciality", specialityService.selectById(id));
        return "speciality/edit";
    }

    @PutMapping("edit/{id}")
    public String editSpeciality(
            @PathVariable long id,
            @ModelAttribute final SpecialityRequest speciality,
            Model model) {
        specialityService.update(id, speciality);
        return "redirect:/speciality";
    }

    @PostMapping("create")
    public String createSpeciality(@ModelAttribute final SpecialityRequest speciality, Model model) {
        specialityService.create(speciality);
        return "redirect:/speciality";
    }
}
