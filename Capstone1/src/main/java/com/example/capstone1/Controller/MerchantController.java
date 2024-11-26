package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/Merchant")
@RequiredArgsConstructor

public class MerchantController {

    private final MerchantService serviceMe;

    @GetMapping("/getMerchant")
    public ResponseEntity get(){

        ArrayList<Merchant> array=serviceMe.getMerchant();
        return ResponseEntity.status(200).body(array);
    }

    @PostMapping("/addMerchant")
    public ResponseEntity add(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceMe.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/updateMerchant/{id}")
    public ResponseEntity updateArticles(@PathVariable String id, @Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = serviceMe.updateMerchant(merchant, id);
        if (isUpdated){
            return ResponseEntity.status(200).body("Updated");
        }else {
            return  ResponseEntity.status(400).body("Id not Found");
        }


    }



    @DeleteMapping("/DeleteMerchant/{id}")
    public  ResponseEntity deleteArticles(@PathVariable String id){

        boolean isDeleted = serviceMe.deleteMerchant(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Delete");
        }else {
            return ResponseEntity.status(400).body("Sorry not Found");
        }
    }
}
