package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "must id not be Empty!")
  private String id;

    @NotEmpty(message = "must user name not be Empty!")
    @Size(min = 6,message = "must  more than 5 length long")
  private String username;

@NotEmpty(message = "must password not be Empty!")
@Size(min = 7,message = " must password more than 6 length long")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
  private String password;

@NotEmpty(message = "must email not be Empty!")
@Email(message = "must be valid email")
  private String email;

@Pattern(regexp = "(Admin|Customer)")
  private String role;

@NotNull(message = "must balance not be Empty!")
@Positive(message = "must balance number  Positive!")
  private double balance;
}
