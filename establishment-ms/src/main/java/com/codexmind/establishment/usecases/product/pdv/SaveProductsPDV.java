package com.codexmind.establishment.usecases.product.pdv;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.dto.SaveProductListDTO;
import com.codexmind.establishment.repository.ProductRepository;
import com.codexmind.establishment.usecases.menu.DetailMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveProductsPDV {


    private final ProductRepository repository;

    private final DetailMenu detailMenu;

    public String execute(List<Object[]> productDTOList, Integer idMenu) {

        productDTOList.forEach(data -> {
            SaveProductListDTO productDTO = new SaveProductListDTO();
            productDTO.setName((String) data[0]);
            productDTO.setPrice(BigDecimal.valueOf((Double) data[1]));
            productDTO.setEstoque((Integer) data[2]);
            productDTO.setEstablishmentId((Integer) data[3]);

            var product = Product.builder()
                    .name(productDTO.getName())
                    .price(productDTO.getPrice())
                    .estoque(productDTO.getEstoque())
                    .menu(detailMenu.execute(idMenu))
                    .build();
             repository.save(product);
        });
        return "Products saved successfully.";
    }
}
