package com.capitole.challenge.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
    @Query("SELECT p FROM PRICES p WHERE p.product_id = ?2 and p.brand_id = ?3 AND ?1 BETWEEN p.start_date AND p.end_date")
    List<ProductPrice> findProductPrices(LocalDateTime date, Long productId, Long brandId);
}
