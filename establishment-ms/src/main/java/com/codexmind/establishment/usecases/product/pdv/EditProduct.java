package com.codexmind.establishment.usecases.product.pdv;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class EditProduct {

    private final ProductRepository productRepository;

    public EditProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(ProductDTO productDTO, Integer id){
        var productFinded = productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Produtonao encontrado!"));
        productFinded.setName(productDTO.getName());
        productFinded.setEstoque(productDTO.getEstoque());
        productFinded.setPrice(productDTO.getPrice());
        return productRepository.save(productFinded);
    }
}
