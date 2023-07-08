package com.capitole.challenge.infrastructure.db;

import com.capitole.challenge.infrastructure.model.ProductPriceModel;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPriceModel, Long> {
    @Query("SELECT p FROM PRICES p WHERE p.productId = ?2 and p.brandId = ?3 AND ?1 BETWEEN p.startDate AND p.endDate")
    List<ProductPriceModel> findProductPrices(LocalDateTime date, Long productId, Long brandId);
}
