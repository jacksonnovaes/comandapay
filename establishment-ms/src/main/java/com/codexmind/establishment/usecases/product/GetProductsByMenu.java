package com.codexmind.establishment.usecases.product;


import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductsByMenu {

    private final ProductRepository productRepository;

    public Page<Product> execute(Integer id, Integer page, Integer linesPerPge, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPge, Sort.Direction.valueOf(direction), orderBy);

        return productRepository.getProductdsByMenu(id, pageRequest);
    }
}
