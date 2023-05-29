package com.example.purchasems.Controller;

import com.example.purchasems.Model.Customer;
import com.example.purchasems.Model.Purchase;
import com.example.purchasems.Service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @Operation(tags = {"Purchase"},hidden = true)
    public String start(){
        return "Logged in!";
    }

    @GetMapping("/purchase/list")
    @Operation(
            tags = {"Purchase"},
            operationId = "listPurchases",
            description = "List all Purchases",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema =
                    @Schema(implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Successful response" )}
    )
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping("/purchase/buy")
    @Operation(
            tags = {"Purchase"},
            operationId = "addPurchase",
            description = "Add a Purchase to DB",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(implementation = Purchase.class))
            ),
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(schema =
                    @Schema(implementation = Purchase.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Successful response")}
    )
    public String buy(@RequestParam List<Long> itemIds, @RequestParam long customerId) {
        return purchaseService.buy(itemIds, customerId);
    }
}
