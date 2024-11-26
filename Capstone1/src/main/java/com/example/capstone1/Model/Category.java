package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "your id is Empty!")
    private String id;

    @NotEmpty(message = "your name is Empty!")
    @Size(min = 4,message = "must more than 3 length long")
    private String name;


}
