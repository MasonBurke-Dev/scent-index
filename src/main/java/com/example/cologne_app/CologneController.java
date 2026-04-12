package com.example.cologne_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/colognes")
public class CologneController {

    private final CologneRepository repo;

    public CologneController(CologneRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("colognes", repo.findAll());
        return "colognes";
    }

    @GetMapping("/cologne/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("cologne", repo.findById(id).orElse(null));
        return "cologne";
    }
}