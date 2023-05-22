package com.example.purchasems.Model;

import jakarta.persistence.*;
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

    @ElementCollection // FRÅGA ROBERT SENARE
    private List<Long> itemIDs;
    private long CustomerID;
//    @OneToOne
//    @JoinColumn
//    private Customer customer;


//    @ManyToMany
//    @JoinTable
//    List<Item> items = new ArrayList<>();



    public Purchase(long CustomerID, List<Long> itemIDs){
        this.CustomerID = CustomerID;
        this.itemIDs = itemIDs;
    }


}