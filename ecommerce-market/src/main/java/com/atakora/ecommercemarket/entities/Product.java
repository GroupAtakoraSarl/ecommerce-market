package com.atakora.ecommercemarket.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@Table(name = "produit")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private  double prix;
    private boolean available;
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
