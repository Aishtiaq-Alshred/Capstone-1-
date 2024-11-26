package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
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

    @PostMapping("/rateProduct/{userId}/{productId}")
    public ResponseEntity rateProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable int rating) {
        boolean isRated = serviceM.rateProduct(userId, productId, rating);
        if (isRated) {
            return ResponseEntity.status(200).body("Product rated successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to rate product. Check IDs or rating range.");
        }
    }

    @PostMapping("/sendNotification/{userId}")
    public ResponseEntity sendNotification(@PathVariable String userId, @PathVariable String message) {
        boolean isSent = serviceM.sendOrderNotification(userId, message);
        if (isSent) {
            return ResponseEntity.status(200).body("Notification sent successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to send notification. Check user ID.");
        }


    }


    @PutMapping("/addDiscount/{merchantId}/{productId}")
    public ResponseEntity addDiscount(@PathVariable String merchantId, @PathVariable String productId, @PathVariable double discount) {
        boolean isDiscounted = serviceM.addDiscount(merchantId, productId, discount);
        if (isDiscounted) {
            return ResponseEntity.status(200).body("Discount added successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to add discount. Check IDs.");
        } }


        @PostMapping("/reorderProduct/{userId}/{productId}")
        public ResponseEntity reorderProduct(@PathVariable String userId, @PathVariable String productId) {
            boolean isReordered = serviceM.reorderProduct(userId, productId);
            if (isReordered) {
                return ResponseEntity.status(200).body("Product reordered successfully.");
            } else {
                return ResponseEntity.status(400).body("Unable to reorder product. Check stock or ID.");
            }
        }

        @PostMapping("/customOffers/{userId}")
        public ResponseEntity getCustomOffers(@PathVariable String userId) {
            ArrayList<Product> offers = serviceM.getCustomOffers(userId);
            if (!offers.isEmpty()) {
                return ResponseEntity.status(200).body(offers);
            } else {
                return ResponseEntity.status(400).body("No custom offers available for this user.");
            }
        }


        @PostMapping("/sendGift/{senderId}/{receiverId}/{productId}")
        public ResponseEntity sendGift(@PathVariable String senderId, @PathVariable String receiverId, @PathVariable String productId) {
            boolean isSent = serviceM.sendGift(senderId, receiverId, productId);
            if (isSent) {
                return ResponseEntity.status(200).body("Gift sent successfully.");
            } else {
                return ResponseEntity.status(400).body("Unable to send gift. Check IDs or stock.");
            }
        }

        @PostMapping("/returnProduct/{userId}/{productId}")
        public ResponseEntity returnProduct(@PathVariable String userId, @PathVariable String productId) {
            boolean isReturned = serviceM.returnProduct(userId, productId);
            if (isReturned) {
                return ResponseEntity.status(200).body("Product returned successfully.");
            } else {
                return ResponseEntity.status(400).body("Unable to return product. Check IDs or orders.");
            }
        }





    }














