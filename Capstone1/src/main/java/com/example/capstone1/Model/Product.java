package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "your id is Empty!")
    private String id;

    @NotEmpty(message = "your name is Empty!")
    private String name;

    @NotNull(message = "your price is Empty!")
    @Positive(message = "must be positive number")
    private double price;

    @NotEmpty(message = "your categoryID is Empty!")
    private String categoryID;


    private ArrayList<Integer> ratings;
    private double discount;
    private int stock;

}
