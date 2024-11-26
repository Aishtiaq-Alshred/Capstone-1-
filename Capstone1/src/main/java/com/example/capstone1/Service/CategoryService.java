package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category>array=new ArrayList<>();

    public ArrayList<Category> getCategory(){
        return array;
    }

    public void addCategory(Category category){
        array.add(category);
    }

    public boolean updateCategory(Category category,String id){

        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.remove(i);
                return true;
            }
        }
        return false;

    }
}
