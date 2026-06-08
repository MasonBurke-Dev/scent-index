package com.example.cologne_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller // 1
@RequestMapping("/colognes")
public class CologneController {

    // Hardcoded picks: widely known bestsellers in the dataset (by rating count)
    private static final List<String> TRENDING_NAMES = List.of(
            "sauvage",
            "aventus",
            "bleu-de-chanel",
            "jazz-club"
    );

    private final CologneRepository repository;

    public CologneController(CologneRepository repository) {
        this.repository = repository;
    }

    public static List<Cologne> fetchTrending(CologneRepository repository) {
        return TRENDING_NAMES.stream()
                .map(repository::findByName)
                .flatMap(Optional::stream)
                .toList();
    }

    // 2. GET request for /colognes/
    @GetMapping("/")
    public String getTrending(Model model) {
        model.addAttribute("trendingColognes", fetchTrending(repository));
        model.addAttribute("pageTitle", "Scent Index");
        return "index";
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