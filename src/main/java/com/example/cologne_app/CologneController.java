package com.example.cologne_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.List;

@Controller // 1
@RequestMapping("/colognes")
public class CologneController {

    private final CologneRepository repository;

    public CologneController(CologneRepository repository) {
        this.repository = repository;
    }

    // 2. GET request for /colognes/
    @GetMapping("/")
    public String getTrending(Model model) {
        // Query database for the first 4 entries
        List<Cologne> trending = repository.findAll().stream().limit(4).toList(); // 3
        model.addAttribute("trendingColognes", trending); // 4
        return "index"; // 5
    }

    // 6. GET request for /colognes/cologne/{id}
    @GetMapping("/cologne/{id}")
    public String getCologneDetails(@PathVariable("id") Long id, Model model) {
        // Query record by unique ID; throw error if missing
        Cologne cologne = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id)); // 7

        model.addAttribute("cologne", cologne);
        return "cologne"; // 8
    }
}