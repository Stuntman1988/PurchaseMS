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
        log.info("ID" + itemIds + " customerID" + customerId);
        try {
            Customer customer = restTemplate.getForObject("http://CustomerMS:8080/customers/" + customerId, Customer.class);
            if (customer == null) {
                return "Invalid customer ID";
            }
            long custId =customer.getId();

            List<Long> items = new ArrayList<>();
            for (Long itemId : itemIds) {
                Item item = restTemplate.getForObject("http://ItemMS:8080/items/" + itemId, Item.class);
                if (item == null) {
                    return "Invalid product ID: " + itemId;
                }
                items.add(item.getId());
            }
            log.info("ITEM " + items + " - CUSTOMER " + custId);
            String response = purchaseService.buy(items, custId);
            log.warn("RESPONSE = " + response);
            return response;
        } catch (Exception e) {
            log.warn("+" + Arrays.toString(e.getStackTrace()));
            log.error("Exception" + e);
            return "Something went wrong!";
        }
    }

//    @GetMapping("/{customerId}")
//    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
//        return purchaseService.getCustomerPurchases(customerId);
//    }
}
