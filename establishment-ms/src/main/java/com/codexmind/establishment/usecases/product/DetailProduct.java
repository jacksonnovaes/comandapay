package com.codexmind.establishment.usecases.product;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailProduct {


    private final ProductRepository repository;

    public Product execute(Integer id) {

        var product = repository.findById(id);
        return product.orElseThrow(() -> new EntityNotFoundException(
                "Object Not Found! id:  " + id + " type: " + Product.class.getName()
        ));
    }
}
