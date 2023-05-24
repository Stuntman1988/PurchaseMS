package com.example.purchasems.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
//    @GeneratedValue
    private long id;
    private String ssn;
    private String name;

    public Customer(String ssn, String name) {
        this.name = name;
        this.ssn = ssn;
    }
}