package com.capitole.challenge.application.config;

import com.capitole.challenge.infrastructure.db.ProductPriceRepository;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductPriceConfig {

    @Bean
    public FindProductPriceUseCase getProductPriceUseCase(ProductPriceRepository repository) {
        return new FindProductPriceUseCase(repository);
    }
}
