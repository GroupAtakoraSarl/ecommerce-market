package com.atakora.ecommercemarket.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("could not found prouduct with id"+ id);
    }

}
