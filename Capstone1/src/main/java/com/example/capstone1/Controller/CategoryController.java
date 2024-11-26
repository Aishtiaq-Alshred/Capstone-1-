package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService serviceC;

    @GetMapping("/getCategory")
    public ResponseEntity get(){

        ArrayList<Category>array=serviceC.getCategory();
        return ResponseEntity.status(200).body(array);
    }

    @PostMapping("/addCategory")
    public ResponseEntity add(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceC.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity updateArticles(@PathVariable String id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = serviceC.updateCategory(category, id);
        if (isUpdated){
            return ResponseEntity.status(200).body("Updated");
        }else {
            return  ResponseEntity.status(400).body("Id not Found");
        }


    }



    @DeleteMapping("/DeleteCategory/{id}")
    public  ResponseEntity deleteArticles(@PathVariable String id){

        boolean isDeleted = serviceC.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Delete");
        }else {
            return ResponseEntity.status(400).body("Sorry not Found");
        }
    }
}
