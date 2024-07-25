package com.codexmind.establishment.usecases.product;

import com.codexmind.establishment.domain.Estoque;
import com.codexmind.establishment.domain.Menu;
import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveProduct {


    private final ProductRepository repository;

    public Product execute(ProductDTO productDTO) {

        var estoque = Estoque.builder()
                .quantity(productDTO.getEstoque())
                .build();
        var menu = Menu.builder()
                .id(productDTO.getMenu())
                .build();
        var product = Product.builder()
                .name(productDTO.getName())
                .estoque(estoque)
                .menu(menu)
                .build();

        return repository.save(product);
    }
}
