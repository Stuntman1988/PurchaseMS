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

    //@ElementCollection // FRÅGA ROBERT SENARE
//    @OneToMany
//    @JoinTable
//    private List<Item> items;
//
//    @OneToOne
//    @JoinColumn
//    private Customer customer;

    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);

//    @OneToOne
//    @JoinColumn
//    @Column
//    @ElementCollection(targetClass = Customer.class)
//    @Transient
    private Long customerId;


//    @ManyToMany
//    @JoinTable
//    @Column
//    @Transient
    @ElementCollection
    private List<Long> items;

    public Purchase(Long customer, List<Long> items){
        this.customerId = customer;
        this.items = items;
    }
}