package com.example.purchasems.Service;


import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Item;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Repo.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchaseService {

    public final PurchaseRepo purchaseRepo;


    public PurchaseService(PurchaseRepo purchaseRepo){
        this.purchaseRepo = purchaseRepo;
    }

    public List<Purchase> getAllPurchase(){
        return purchaseRepo.findAll();
    }

    public String buy(List<Item> items, Customer customer) {
        try {
            purchaseRepo.save(new Purchase(customer, items));
            return "Purchase successful";
        }catch (Exception e){
            return "WRONG";
        }
    }
//    public List<Purchase> getCustomerPurchases(long id){
//        return purchaseRepo.findAllByCustomerId(id);
//    }


}
