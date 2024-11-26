package com.example.capstone1.Service;


import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockServise {

    ArrayList<MerchantStock> array = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Merchant> merchants = new ArrayList<>();


    public ArrayList<MerchantStock> get() {
        return array;
    }


    public void addStock(MerchantStock merchantStock) {
        array.add(merchantStock);
    }


    public boolean updateStock(MerchantStock merchantStock, String id) {

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(id)) {
                array.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStock(String id) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(id)) {
                array.remove(i);
                return true;
            }
        }
        return false;

    }



    public boolean addedStock(String productId, String merchantId, int additionalStock) {
        for (MerchantStock stock : array) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                stock.setStock(stock.getStock() + additionalStock);
                return true;
            }
        }
        return false;
    }



    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }



    public String buyProduct(String userId, String productId, String merchantId) {
        MerchantStock stock = null;
        for (MerchantStock s : array) {
            if (s.getProductId().equals(productId) && s.getMerchantId().equals(merchantId) && s.getStock() > 0) {
                stock = s;
                break;
            }
        }
        if (stock == null) {
            return "Product not available in stock.";
        }

        User user = findUserById(userId);
        Product product = findProductById(productId);
        if (user == null || product == null) {
            return "Invalid user or product ID.";
        }
        if (user.getBalance() < product.getPrice()) {
            return "Insufficient balance.";
        }

        stock.setStock(stock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return "Purchase successful.";
    }



    public boolean updateBalance(String userId, double amount) {
        User user = findUserById(userId);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            return true;
        }
        return false;
    }


    public int deleteLowStockProducts(int threshold) {
        int count = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getStock() < threshold) {
                array.remove(i);
                count++;
                i--; // لتجنب تخطي العناصر بسبب الحذف
            }
        }
        return count;
    }

    public ArrayList<Product> getProductsByCategory(String categoryId) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategoryID().equals(categoryId)) {
                result.add(product);
            }
        }
        return result;
    }


    public boolean updateProductName(String productId, String newName) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setName(newName);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Product> getProductsByMerchant(String merchantId) {
        ArrayList<Product> merchantProducts = new ArrayList<>();
        for (MerchantStock stock : array) {
            if (stock.getMerchantId().equals(merchantId)) {
                Product product = findProductById(stock.getProductId());
                if (product != null) {
                    merchantProducts.add(product);
                }
            }
        }
        return merchantProducts;
    }


    public int checkStock(String productId, String merchantId) {
        for (MerchantStock stock : array) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                return stock.getStock();
            }
        }
        return -1; // يعني أن المنتج غير متوفر
    }


    public Merchant findMerchantById(String merchantId){

        for (Merchant merchant:merchants){
            if (merchant.getId().equals(merchantId)){
                return merchant;
            }
        }
        return null;
    }

    public ArrayList<Merchant> getMerchantsByProduct(String productId) {
        ArrayList<Merchant> productMerchants = new ArrayList<>();
        for (MerchantStock stock : array) {
            if (stock.getProductId().equals(productId)) {
                Merchant merchant = findMerchantById(stock.getMerchantId());
                if (merchant != null) {
                    productMerchants.add(merchant);
                }
            }
        }
        return productMerchants;
    }

    public ArrayList<User> getUsersByLowBalance(double balance) {
        ArrayList<User> lowBalanceUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getBalance() < balance) {
                lowBalanceUsers.add(user);
            }
        }
        return lowBalanceUsers;
    }





}



