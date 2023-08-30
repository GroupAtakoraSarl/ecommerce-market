package com.atakora.ecommercemarket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@NotBlank
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private @NotBlank String categoryName;
    private @NotBlank String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
