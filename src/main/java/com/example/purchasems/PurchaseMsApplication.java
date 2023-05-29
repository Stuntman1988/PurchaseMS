package com.example.purchasems;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Purchase API", version = "1.0.0"),
        servers = {@Server(url = "http://localhost:8082")},
        tags = {@Tag(name = "PurchaseMS", description = "API to buy and list purchases")}
)
public class PurchaseMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseMsApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
     }
}
