package com.atakora.ecommercemarket.repository;


import com.atakora.ecommercemarket.entities.Shop;

import com.atakora.ecommercemarket.entities.Subscription;
import com.atakora.ecommercemarket.entities.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    void deleteByBuyerAndShop(User buyer, Shop shop);
}
