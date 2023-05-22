package com.example.purchasems.Repo;

import com.example.purchasems.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {

//    List<Purchase> findAllByCustomerId(long id);

}
