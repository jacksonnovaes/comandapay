package com.codexmind.establishment.usecases.product;


import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetProductsByMenu {

    private final ProductRepository productRepository;

    public Set<Product> execute(Integer id){

        return productRepository.getProductdsByMenuandEstablishmente(id);
    }
}
