package com.example.purchasems.Service;


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

    public String buy(List<Long> itemIDs, long customerId) {
        Purchase p1 = new Purchase(customerId,itemIDs);
        purchaseRepo.save(p1);
        return "temp success";
    }
//    public List<Purchase> getCustomerPurchases(long id){
//        return purchaseRepo.findAllByCustomerId(id);
//    }


}
