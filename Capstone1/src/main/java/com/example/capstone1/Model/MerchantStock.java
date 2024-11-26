package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {


    @NotEmpty(message = "your id is Empty!")
    private String id;

    @NotEmpty(message = "your product id is Empty!")
    private String productId;

    @NotEmpty(message = "your merchant id is Empty!")
    private String merchantId;


    @NotNull(message = "your stock is Empty!")
   @Min(value = 11,message = "must  more than 10 at start)")
    private int stock;
}
