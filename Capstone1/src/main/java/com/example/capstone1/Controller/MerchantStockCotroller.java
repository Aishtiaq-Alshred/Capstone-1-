package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.MerchantStockServise;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/MerchantStock")
@RequiredArgsConstructor
public class MerchantStockCotroller {

    private final MerchantStockServise serviceM;

    @GetMapping("/getStock")
    public ResponseEntity get(){

        ArrayList<MerchantStock> array=serviceM.get();
        return ResponseEntity.status(200).body(array);
    }

    @PostMapping("/addStock")
    public ResponseEntity add(@RequestBody @Valid MerchantStock merchant, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceM.addStock(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("/updateStock/{id}")
    public ResponseEntity update(@PathVariable String id,  @RequestBody @Valid MerchantStock merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = serviceM.updateStock(merchant, id);
        if (isUpdated){
            return ResponseEntity.status(200).body("Updated");
        }else {
            return  ResponseEntity.status(400).body(new ApiResponse("Id not Found"));
        }


    }



    @DeleteMapping("/DeleteStock/{id}")
    public  ResponseEntity delete(@PathVariable String id){

        boolean isDeleted = serviceM.deleteStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Delete");
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("Sorry not Found"));
        }
    }


    @PutMapping("/addStock/{productId}/{merchantId}")
    public ResponseEntity addStock(@PathVariable String productId, @PathVariable  String merchantId, @PathVariable  int additionalStock) {
        boolean isAdded = serviceM.addedStock(productId, merchantId, additionalStock);
        if (isAdded) {
            return ResponseEntity.status(200).body("Stock added successfully.");
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Product or Merchant not found."));
        }
    }

    @PostMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable  String userId, @PathVariable  String productId, @PathVariable String merchantId) {
        String result = serviceM.buyProduct(userId, productId, merchantId);
        if (result.equals("Purchase successful.")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    @PutMapping("/updateBalance/{userId}/{amount}")
    public ResponseEntity updateBalance(@PathVariable String userId, @PathVariable double amount) {
        boolean isUpdated = serviceM.updateBalance(userId, amount);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Balance updated successfully.");
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found."));
    }

    @DeleteMapping("/deleteLowStockProducts/{threshold}")
    public ResponseEntity deleteLowStockProducts(@PathVariable int threshold) {
        int deletedCount = serviceM.deleteLowStockProducts(threshold);
        if (deletedCount > 0) {
            return ResponseEntity.status(200).body("Deleted " + deletedCount + " products with low stock.");
        } else {
            return ResponseEntity.status(400).body("No products found with stock below " + threshold);
        }
    }


    @PostMapping("/getProductsByCategory/{categoryId}")
    public ResponseEntity getProductsByCategory(@PathVariable String categoryId) {
        ArrayList<Product> products = serviceM.getProductsByCategory(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.status(400).body("No products found in this category.");
        } else {
            return ResponseEntity.status(200).body(products);
        }
    }

    @PutMapping("/updateProductName/{productId}/{newName}")
    public ResponseEntity updateProductName(@PathVariable String productId, @PathVariable String newName) {
        boolean updated = serviceM.updateProductName(productId, newName);
        if (updated) {
            return ResponseEntity.status(200).body("Product name updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Product not found.");
        }
    }



    @GetMapping("/getProductsByMerchant/{merchantId}")
    public ResponseEntity getProductsByMerchant(@PathVariable String merchantId) {
        ArrayList<Product> products = serviceM.getProductsByMerchant(merchantId);
        if (products.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No products found for this merchant."));
        }
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/checkStock/{productId}/{merchantId}")
    public ResponseEntity checkStock(@PathVariable String productId, @PathVariable String merchantId) {
        int stock = serviceM.checkStock(productId, merchantId);
        if (stock >= 0) {
            return ResponseEntity.status(200).body("Stock available: " + stock);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not available at this merchant."));
    }

    @GetMapping("/getMerchantsByProduct/{productId}")
    public ResponseEntity getMerchantsByProduct(@PathVariable String productId) {
        ArrayList<Merchant> merchants = serviceM.getMerchantsByProduct(productId);
        if (merchants.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No merchants found for this product."));
        }
        return ResponseEntity.status(200).body(merchants);
    }

    @GetMapping("/getUsersByLowBalance/{balance}")
    public ResponseEntity getUsersByLowBalance(@PathVariable double balance) {
        ArrayList<User> users = serviceM.getUsersByLowBalance(balance);
        if (users.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("No users found with balance below " + balance));
        }
        return ResponseEntity.status(200).body(users);
    }






}





