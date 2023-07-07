package com.capitole.challenge.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public final class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public ProductPrice(
            Long brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer priceList,
            Long productId,
            Integer priority,
            BigDecimal price,
            String currency
    ) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public ProductPrice() { }

    public Long brandId() {
        return brandId;
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Integer priceList() {
        return priceList;
    }

    public Long productId() {
        return productId;
    }

    public Integer priority() {
        return priority;
    }

    public BigDecimal price() {
        return price;
    }

    public String currency() {
        return currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ProductPrice) obj;
        return Objects.equals(this.brandId, that.brandId) &&
                Objects.equals(this.startDate, that.startDate) &&
                Objects.equals(this.endDate, that.endDate) &&
                Objects.equals(this.priceList, that.priceList) &&
                Objects.equals(this.productId, that.productId) &&
                Objects.equals(this.priority, that.priority) &&
                Objects.equals(this.price, that.price) &&
                Objects.equals(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }
}
