package com.atakora.ecommercemarket.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "command_item")
public class CommandItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "command_id")
    private Command command;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double totalPrice;
}
