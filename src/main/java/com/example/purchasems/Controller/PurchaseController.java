package com.example.purchasems.Controller;

import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Item;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceBaseUrl;

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllItems(){
        return purchaseService.getAllPurchase();
    }

    @PostMapping("/buy")
    public String buy(@RequestBody  List<Long> itemIds, @RequestParam long customerId){
        Customer customer = restTemplate.getForObject("http://localhost:8080/customers/" + customerId, Customer.class);
        if (customer == null) {
            return "Invalid customer ID";
        }

        List<Item> items = new ArrayList<>();
        for(Long itemId : itemIds) {
            Item item = restTemplate.getForObject("http://localhost:8080/items/" +itemId, Item.class);
            if(item == null){
                return "Invalid product ID: " + itemId;
            }
            items.add(item);
        }
       return purchaseService.buy(items, customer);
    }

//    @GetMapping("/{customerId}")
//    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
//        return purchaseService.getCustomerPurchases(customerId);
//    }
}
