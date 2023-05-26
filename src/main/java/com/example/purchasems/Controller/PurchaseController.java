package com.example.purchasems.Controller;

import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping()
public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping()
    public String start(){
        return "Logged in!";
    }

    @GetMapping("/purchase/list")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping("/purchase/buy")
    public String buy(@RequestParam List<Long> itemIds, @RequestParam long customerId) {
        return purchaseService.buy(itemIds, customerId);
    }
}
