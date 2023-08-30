package com.atakora.ecommercemarket.controller;

import com.atakora.ecommercemarket.entities.Shop;
import com.atakora.ecommercemarket.entities.User;
import com.atakora.ecommercemarket.repository.ShopRepository;
import com.atakora.ecommercemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopController(ShopRepository shopRepository, UserRepository userRepository) {
        this.shopRepository = shopRepository;
    }

    // Afficher le formulaire pour créer une nouvelle boutique
    @GetMapping("/new")
    public String showCreateShopForm(Model model, @AuthenticationPrincipal User seller) {
        Shop shop = new Shop();
        shop.setSeller(seller);
        model.addAttribute("shop", shop);
        return "create-shop-form"; // Remplacez cette vue par votre propre formulaire de création de boutique
    }

    // Enregistrer la nouvelle boutique créée par le vendeur
    @PostMapping
    public String createShop(@ModelAttribute("shop") Shop shop) {
        shopRepository.save(shop);
        return "redirect:/shops/" + shop.getId(); // Redirigez vers la page de la boutique nouvellement créée
    }

    // Afficher les détails de la boutique
    @GetMapping("/{id}")
    public String showShopDetails(@PathVariable Long id, Model model) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Boutique invalide : " + id));
        model.addAttribute("shop", shop);
        // Ajoutez les autres informations à afficher sur la page de la boutique
        return "shop-details"; // Remplacez cette vue par votre propre page d'affichage de la boutique
    }
}
