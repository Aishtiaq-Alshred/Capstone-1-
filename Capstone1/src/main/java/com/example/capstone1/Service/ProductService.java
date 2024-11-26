package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {


    private ArrayList<Category> categories=new ArrayList<>();
    private ArrayList<Product> products=new ArrayList<>();


    public ArrayList<Product> get(){
        return products;
    }

    public boolean add(Product product, String categoryID) {
        for (Category category : categories) {
            if (category.getId().equals(categoryID)) {
                products.add(product);
                return true;
            }
        }

        return false;
    }


    public boolean update(Product product,String id){

        for (int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;

    }
}
