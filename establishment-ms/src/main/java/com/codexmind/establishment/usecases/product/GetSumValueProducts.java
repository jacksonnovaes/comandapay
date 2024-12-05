package com.codexmind.establishment.usecases.product;

import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class GetSumValueProducts {

    private final ProductRepository productRepository;

    public BigDecimal execute(Set<Long> productIds) {
        return productRepository.getTotalAmountByIds(productIds);
    }
}
