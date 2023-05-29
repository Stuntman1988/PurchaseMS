package com.example.purchasems.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @NotNull(message = "\"items\" can not be empty!\n")
    private Long customerId;

    @ElementCollection
    @NotNull(message = "\"items\" can not be empty!\n")
    private List<Long> items;

    public Purchase(Long customer, List<Long> items){
        this.customerId = customer;
        this.items = items;
    }
}