package com.capitole.challenge.domain.exception;

public class ProductPriceNotFoundException extends RuntimeException{
    public ProductPriceNotFoundException() {
        super("Product price not found");
    }
}
