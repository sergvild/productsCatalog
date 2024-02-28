package com.fiphiker.product.exception;

public class ProductNotFoundException extends Exception{
    private String message;

    public ProductNotFoundException(String message){
        super(message);
    }
}
