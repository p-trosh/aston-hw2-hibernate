package ru.trosh.astontrainee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.trosh.astontrainee.dao.SpecialityDAO;
import ru.trosh.astontrainee.model.Speciality;

@Controller
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityDAO dao;

    @GetMapping()
    public String specialities(Model model) {
        model.addAttribute("specialities", dao.selectAll());
        return "speciality/index";
    }

    @DeleteMapping("delete/{id}")
    public String deleteSpeciality(@PathVariable long id) {
        dao.delete(id);
        return "redirect:/speciality";
    }

    @GetMapping("create")
    public String createSpeciality(Model model) {
        return "speciality/edit";
    }

    @GetMapping("edit/{id}")
    public String editSpeciality(@PathVariable long id, Model model) {
        model.addAttribute("speciality", dao.selectById(id));
        return "speciality/edit";
    }

    @PutMapping("edit")
    public String editSpeciality(@ModelAttribute final Speciality speciality, Model model) {
        dao.update(speciality);
        return "redirect:/speciality";
    }

    @PostMapping("create")
    public String createSpeciality(@ModelAttribute final Speciality speciality, Model model) {
        dao.create(speciality);
        return "redirect:/speciality";
    }





}
