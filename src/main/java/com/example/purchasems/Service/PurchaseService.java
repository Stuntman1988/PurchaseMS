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
//@Transactional //*****
public class PurchaseService {

    @Autowired
    private RestTemplate restTemplate;

    public final PurchaseRepo purchaseRepo;

    public PurchaseService(PurchaseRepo purchaseRepo){
        this.purchaseRepo = purchaseRepo;
    }

    public List<Purchase> getAllPurchases(){
        return purchaseRepo.findAll();
    }

    public String buy(List<Long> items, Long customerId) {
        try {
            Customer customer = restTemplate.getForObject("http://CustomerMS:8080/customers/" + customerId, Customer.class);
            if (customer == null) {
                return "Invalid customer ID";
            }
            for (Long itemId : items) {
                Item item = restTemplate.getForObject("http://ItemMS:8080/items/" + itemId, Item.class);
                if (item == null) {
                    return "Invalid product ID: " + itemId;
                }
            }
            purchaseRepo.save(new Purchase(customerId, items));
            return "Purchase successful";
        } catch (Exception e) {
            return "Something went wrong!";
        }
    }
//    public List<Purchase> getCustomerPurchases(long id){
//        return purchaseRepo.findAllByCustomerId(id);
//    }

}
