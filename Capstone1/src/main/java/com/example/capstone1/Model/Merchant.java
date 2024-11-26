package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Merchant {

    @NotEmpty(message = "must id not be empty!")
    private String id;

    @NotEmpty(message = "must name not be empty")
    @Size(min = 4,message = "must  more than 3 length long")
    private String name;

}
