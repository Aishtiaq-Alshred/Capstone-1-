package com.example.capstone1.Service;


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





    public boolean rateProduct(String userId, String productId, int rating) {
        User user = findUserById(userId);
        Product product = findProductById(productId);

        if (user != null && product != null && rating >= 1 && rating <= 5) {
            product.getRatings().add(rating);
            return true;
        }
        return false;
    }


    public boolean sendOrderNotification(String userId, String message) {
        User user = findUserById(userId);
        if (user != null) {
            user.getNotifications().add(message);
            return true;
        }
        return false;
    }

    public boolean addDiscount(String merchantId, String productId, double discount) {
        for (MerchantStock stock : array) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                Product product = findProductById(productId);
                if (product != null) {
                    product.setDiscount(discount);
                    return true;
                }
            }
        }
        return false;
    }



    public boolean reorderProduct(String userId, String productId) {
        User user = findUserById(userId);
        Product product = findProductById(productId);

        if (user != null && product != null && product.getStock() > 0) {
            user.getOrders().add(productId);
            product.setStock(product.getStock() - 1);
            return true;
        }
        return false;
    }


    public ArrayList<Product> getCustomOffers(String userId) {
        User user = findUserById(userId);
        ArrayList<Product> offers = new ArrayList<>();

        if (user != null) {
            for (String productId : user.getViewedProducts()) {
                Product product = findProductById(productId);
                if (product != null && product.getDiscount() > 0) {
                    offers.add(product);
                }
            }
        }
        return offers;
    }


    public boolean sendGift(String senderId, String receiverId, String productId) {
        User sender = findUserById(senderId);
        User receiver = findUserById(receiverId);
        Product product = findProductById(productId);

        if (sender != null && receiver != null && product != null && product.getStock() > 0) {
            receiver.getGifts().add(productId);
            product.setStock(product.getStock() - 1);
            return true;
        }
        return false;
    }

    public boolean returnProduct(String userId, String productId) {
        User user = findUserById(userId);
        Product product = findProductById(productId);

        if (user != null && product != null && user.getOrders().contains(productId)) {
            user.getOrders().remove(productId);
            product.setStock(product.getStock() + 1);
            return true;
        }
        return false;
    }











}



