package com.example.purchasems.Service;


import com.example.purchasems.Controller.PurchaseController;
import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Item;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Repo.PurchaseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
public class PurchaseService {

    public final PurchaseRepo purchaseRepo;

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);


    public PurchaseService(PurchaseRepo purchaseRepo){
        this.purchaseRepo = purchaseRepo;
    }

    public List<Purchase> getAllPurchase(){
        return purchaseRepo.findAll();
    }

    public String buy(List<Item> items, Customer customer) {
        try {
            log.warn("PURCHASESERVICE");
            Purchase purchase = new Purchase(customerId, items);
            log.warn("PURCHASESERVICE2");
            purchaseRepo.save(purchase);
            log.warn("PURCHASESERVICE3");
            return "Purchase successful";
        }catch (Exception e){
            log.error("FEL" + Arrays.toString(e.getStackTrace()));
            log.error("E" + e);
            return "WRONG";
        }
    }
//    public List<Purchase> getCustomerPurchases(long id){
//        return purchaseRepo.findAllByCustomerId(id);
//    }


}
