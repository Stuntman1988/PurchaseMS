package com.example.purchasems.Controller;

import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Item;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping()
public class PurchaseController {

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${user-service.url}")
//    private String userServiceBaseUrl;
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
    //@CrossOrigin
    @PostMapping("/purchase/buy")
    public String buy(@RequestParam List<Long> itemIds, @RequestParam long customerId) {
        return purchaseService.buy(itemIds, customerId);
    }

//    @GetMapping("/{customerId}")
//    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
//        return purchaseService.getCustomerPurchases(customerId);
//    }
}
