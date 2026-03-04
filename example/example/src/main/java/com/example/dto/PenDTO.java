package com.example.dto;

public class PenDTO {
    private Long id;
    private String brand;
    private String color;
    private Double price;

    public PenDTO() {}

    public PenDTO(Long id, String brand, String color, Double price) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
