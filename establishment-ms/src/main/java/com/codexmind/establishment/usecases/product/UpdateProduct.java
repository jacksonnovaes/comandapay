package com.codexmind.establishment.usecases.product;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProduct {


    private final ProductRepository repository;

    public Product execute(Long id, ProductDTO productDTO) {

        var product = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .build();

        return repository.save(product);
    }
}
