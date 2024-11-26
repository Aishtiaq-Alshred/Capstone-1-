package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor

public class UserController {

    private final UserService serviceU;

    @GetMapping("/get")
    public ResponseEntity get(){

        ArrayList<User> array=serviceU.get();
        return ResponseEntity.status(200).body(array);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid User model, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceU.add(model);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticles(@PathVariable String id, @Valid @RequestBody User model, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = serviceU.update(model, id);
        if (isUpdated){
            return ResponseEntity.status(200).body("Updated");
        }else {
            return  ResponseEntity.status(400).body("Id not Found");
        }


    }



    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity deleteArticles(@PathVariable String id){

        boolean isDeleted = serviceU.delete(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Delete");
        }else {
            return ResponseEntity.status(400).body("Sorry not Found");
        }
    }

}
