package com.capitole.challenge.infrastructure.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "PRICES")
@Table(name = "PRICES", schema = "public")
public class ProductPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "product_id")
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public ProductPriceModel() {
    }

    public ProductPriceModel(
            Long id,
            Long brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer priceList,
            Long productId,
            Integer priority,
            BigDecimal price,
            String currency
    ) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public Integer getPriceList() {
        return this.priceList;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }
}
