package com.capitole.challenge.application.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class FindProductPriceRequest {
    @NotNull(message = "Date parameter is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDateTime date;

    @NotNull(message = "Product id parameter is required")
    @Pattern(regexp = "^\\d+$", message = "Invalid Product id")
    private String productId;

    @NotNull(message = "Brand id parameter is required")
    @Pattern(regexp = "^\\d+$", message = "Invalid Brand id")
    private String brandId;

    public FindProductPriceRequest(LocalDateTime date, String productId, String brandId) {
        this.date = date;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
