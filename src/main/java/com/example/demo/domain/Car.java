package com.example.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "cars")
@Data
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private String color;
    private float price;

    public Car(String id, String brand, String model, String color, float price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }
}
