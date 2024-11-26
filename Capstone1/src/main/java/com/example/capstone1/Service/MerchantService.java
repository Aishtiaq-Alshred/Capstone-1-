package com.example.capstone1.Service;
import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {


    ArrayList<Merchant> array=new ArrayList<>();

    public ArrayList<Merchant> getMerchant(){
        return array;
    }

    public void addMerchant(Merchant merchant){
        array.add(merchant);
    }

    public boolean updateMerchant(Merchant merchant,String id){

        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id){
        for (int i=0;i<array.size();i++){
            if(array.get(i).getId().equals(id)){
                array.remove(i);
                return true;
            }
        }
        return false;

    }
}
