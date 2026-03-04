package com.example.cologne_app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@SpringBootApplication
public class CologneAppApplication {

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("serverTime", 30);
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(CologneAppApplication.class, args);
    }

}