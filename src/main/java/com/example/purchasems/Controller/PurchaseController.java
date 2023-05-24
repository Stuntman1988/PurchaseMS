package com.example.purchasems.Controller;

import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Item;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${user-service.url}")
//    private String userServiceBaseUrl;

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllItems() {
        return purchaseService.getAllPurchase();
    }

    @PostMapping("/buy")
    public String buy(@RequestParam List<Long> itemIds, @RequestParam long customerId) {
        log.warn("ID" + itemIds + " custID" + customerId);
        try {
            Customer customer = restTemplate.getForObject("http://CustomerMS:8080/customers/" + customerId, Customer.class);
            if (customer == null) {
                return "Invalid customer ID";
            }
            List<Item> items = new ArrayList<>();
            for (Long itemId : itemIds) {
                Item item = restTemplate.getForObject("http://ItemMS:8080/items/" + itemId, Item.class);
                if (item == null) {
                    return "Invalid product ID: " + itemId;
                }
                items.add(item);
            }
            log.warn("ITEM " + items + " - CUSTOMER " + customer);
            return purchaseService.buy(items, customer);
        } catch (Exception e) {
            log.warn("+" + e.getStackTrace());
            return "hej";
        }

    }

//    @GetMapping("/{customerId}")
//    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
//        return purchaseService.getCustomerPurchases(customerId);
//    }
}
