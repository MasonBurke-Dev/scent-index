package com.example.cologne_app;


import items.Cologne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String home(Model model) {
        ArrayList<Cologne> populist = new ArrayList<>();
        populist.add(new Cologne("Aventus", "Creed"));
        populist.add(new Cologne("Sauvage", "Dior"));
        populist.add(new Cologne("Althair", "Parfums de Marley"));
        model.addAttribute("pageTitle", "Scent Index");
        model.addAttribute("populist",populist);



        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(CologneAppApplication.class, args);
    }

}

