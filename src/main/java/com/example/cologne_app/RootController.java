package com.example.cologne_app;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    private final CologneRepository repository;

    public RootController(CologneRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("pageTitle", "Scent Index");
        model.addAttribute("trendingColognes", CologneController.fetchTrending(repository));
        return "index";
    }
}

