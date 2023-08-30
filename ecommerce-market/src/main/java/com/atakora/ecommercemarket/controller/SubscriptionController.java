package com.atakora.ecommercemarket.controller;

import com.atakora.ecommercemarket.entities.Shop;
import com.atakora.ecommercemarket.entities.Subscription;
import com.atakora.ecommercemarket.entities.User;
import com.atakora.ecommercemarket.repository.ShopRepository;
import com.atakora.ecommercemarket.repository.SubscriptionRepository;
import com.atakora.ecommercemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SubscriptionController {
    private final SubscriptionRepository subscriptionRepository;
    private final ShopRepository shopRepository;
    @Autowired
    public SubscriptionController(UserRepository userRepository, ShopRepository shopRepository, SubscriptionRepository subscriptionRepository){
        this.shopRepository = shopRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping("/{shopId}/subscribe")
    public String subscribeToShop(@PathVariable Long shopId, @AuthenticationPrincipal User buyer){
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique invalide : " + shopId));

        Subscription subscription = new Subscription();
        subscription.setBuyer(buyer);
        subscription.setShop(shop);

        subscriptionRepository.save(subscription);
        return "redirect:/shop/" + shopId;
    }

    @PostMapping("/{shopId}/unsubscribe")
    public String unsubscribeFromShop(@PathVariable Long shopId, @AuthenticationPrincipal User buyer){
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Boutique invalide : " + shopId));

        subscriptionRepository.deleteByBuyerAndShop(buyer, shop);
        return "redirect:/shop/" + shopId;
    }
}
