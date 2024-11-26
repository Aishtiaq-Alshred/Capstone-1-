package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Product")
@RequiredArgsConstructor

public class ProductControoler {

    private final ProductService serviceP;

    @GetMapping("/get")
    public ResponseEntity get(){

        ArrayList<Product> array=serviceP.get();
        return ResponseEntity.status(200).body(array);
    }

    @PostMapping("/add/{categoryID}")
    public ResponseEntity add(@PathVariable String categoryID, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isAdded = serviceP.add(product, categoryID);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Product added successfully."));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Category ID not found."));
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateArticles(@PathVariable String id, @Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = serviceP.update(product, id);
        if (isUpdated){
            return ResponseEntity.status(200).body("Updated");
        }else {
            return  ResponseEntity.status(400).body("Id not Found");
        }


    }



    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity deleteArticles(@PathVariable String id){

        boolean isDeleted = serviceP.delete(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Delete");
        }else {
            return ResponseEntity.status(400).body("Sorry not Found");
        }
    }
}
