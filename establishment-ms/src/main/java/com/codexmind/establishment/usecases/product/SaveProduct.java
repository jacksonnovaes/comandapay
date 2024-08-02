package com.codexmind.establishment.usecases.product;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.ProductDTO;
import com.codexmind.establishment.repository.ProductRepository;
import com.codexmind.establishment.usecases.menu.DetailMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveProduct {


    private final ProductRepository repository;

    private final DetailMenu detailMenu;

    public Product execute(ProductDTO productDTO, Integer idMenu) {



        var product = Product.builder()
                .name(productDTO.getName())
                .estoque(productDTO.getEstoque())
                .menu(detailMenu.execute(idMenu))
                .build();
        return repository.save(product);
    }
}
