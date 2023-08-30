package com.atakora.ecommercemarket.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Entity
@Table(name = "boutique")
@Data
public class Shop {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @OneToOne
    @JoinColumn(name="seller_id")
    private User seller;
    private String name;
    private String description;
    private String address;

    }


