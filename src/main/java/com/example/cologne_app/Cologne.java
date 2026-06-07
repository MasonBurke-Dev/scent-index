package com.example.cologne_app;

import jakarta.persistence.*;





@Entity
public class Cologne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String image;
    private String brand;

    @Column(length = 2000)
    private String description;
    private double price ;

    private Boolean isMale;





    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getBrand() {
        return brand;
    }

    public Boolean getMale() {
        return isMale;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }
}
