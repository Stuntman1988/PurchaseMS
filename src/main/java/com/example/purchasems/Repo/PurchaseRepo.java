package com.example.purchasems.Repo;

import com.example.purchasems.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {}
