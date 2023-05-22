package com.example.purchasems.Controller;

import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllItems(){
        return purchaseService.getAllPurchase();
    }

    @PostMapping("/buy")
    public String buyItem(@RequestParam List<Long> itemIDs, @RequestParam long customerId){
        return purchaseService.buy(itemIDs, customerId);
    }

//    @GetMapping("/{customerId}")
//    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
//        return purchaseService.getCustomerPurchases(customerId);
//    }
}
