package com.example.capstone1.Service;

import com.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> array=new ArrayList<>();

    public ArrayList<User> get(){
        return array;
    }

    public void add(User model){
        array.add(model);
    }

    public boolean update(User model,String id){

        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.set(i,model);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id){
        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.remove(i);
                return true;
            }
        }
        return false;

    }
}
