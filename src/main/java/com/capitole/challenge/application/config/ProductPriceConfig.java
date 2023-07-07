package com.capitole.challenge.application.config;

import com.capitole.challenge.domain.model.ProductPriceRepository;
import com.capitole.challenge.domain.usecase.GetProductPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductPriceConfig {

    @Bean
    public GetProductPriceUseCase getProductPriceUseCase(ProductPriceRepository repository) {
        return new GetProductPriceUseCase(repository);
    }
}
