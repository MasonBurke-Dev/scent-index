package com.example.cologne_app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    private final CologneRepository repository;

    public DataLoader(CologneRepository repository) {
        this.repository = repository;

    }
    @Override
    public void run(String... args) throws Exception{
        if (repository.count() > 0){
            System.out.println("Already exists, skipping load");
            return;

        }
        System.out.println("starting import");
        ClassPathResource resource = new ClassPathResource("data/cleaned-data.csv"); //loads this file into compilation
        List<Cologne> colognes = new ArrayList<>();
        int count = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){

            String line;
            br.readLine(); //skips first row

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length < 8) {
                    continue;
                }
                String id = getVal(parts,0);
                String name = getVal(parts,1);
                String brand = getVal(parts,2);
                String country = getVal(parts,3);
                String gender =getVal(parts,4);

                String rating = getVal(parts,5).replace(",", ".");
                double ratingValue = 0.0;
                try {
                    ratingValue = Double.parseDouble(rating);
                } catch (NumberFormatException e) {
                }
                String yearStr =getVal(parts,7);
                int year = 0;
                try {
                    year = (int) Double.parseDouble(yearStr);
                } catch (NumberFormatException e) {

                }

                String topNotes = getVal(parts,8);
                String midNotes = getVal(parts,9);
                String baseNotes = getVal(parts,10);
                String perfumer = getVal(parts,11);

                StringBuilder sb = new StringBuilder();
                sb.append("A ").append(gender.toLowerCase()).append(" fragrance by ").append(brand);
                if (year > 0) {
                    sb.append(" released in ").append(year);
                }
                if (!country.isEmpty() && !country.equalsIgnoreCase("unknown")) {
                    sb.append(" from ").append(country);
                }
                sb.append(". ");

                if (!topNotes.isEmpty() && !topNotes.equalsIgnoreCase("unknown")) {
                    sb.append("Features top notes of ").append(topNotes).append("; ");
                }
                if (!midNotes.isEmpty() && !midNotes.equalsIgnoreCase("unknown")) {
                    sb.append("middle notes of ").append(midNotes).append("; ");
                }
                if (!baseNotes.isEmpty() && !baseNotes.equalsIgnoreCase("unknown")) {
                    sb.append("and base notes of ").append(baseNotes).append(". ");
                }
                if (!perfumer.isEmpty() && !perfumer.equalsIgnoreCase("unknown")) {
                    sb.append("Created by master perfumer ").append(perfumer).append(".");
                }

                String description = sb.toString().trim();

                double price = 100;

                Cologne cologne = new Cologne();
                cologne.setName(name);
                cologne.setBrand(brand);
                cologne.setDescription(description);
                cologne.setPrice(price);

                cologne.setImage("https://fimgs.net/mdimg/perfume-thumbs/dark-375x500." + id + ".2x.avif");


                colognes.add(cologne);
                count++;


            }









          repository.saveAll(colognes);
            System.out.println("Done!");
        }}
        private String getVal(String[] parts, int index) {
            if (index >= 0 && index < parts.length) {
                return parts[index].trim();
            }
            return "";
        }




    }
