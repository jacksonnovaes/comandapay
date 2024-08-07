package com.codexmind.establishment.usecases.order.pdv;


import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchProductsPDV {

    private final ProductRepository productRepository;

    public Page<Product> execute(String name, Integer idmenu, Integer page, Integer linesPerPge, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);

            return productRepository.searchProductsEstoque(name, idmenu, pageRequest);

    }
}
