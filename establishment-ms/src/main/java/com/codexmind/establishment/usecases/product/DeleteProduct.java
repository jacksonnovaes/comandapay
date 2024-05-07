package com.codexmind.establishment.usecases.product;


import com.codexmind.establishment.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProduct {

    private final ProductRepository repository;

    public void execute(Integer id) {
        var product = repository.findById(id);
        product.ifPresent(repository::delete);
    }
}
