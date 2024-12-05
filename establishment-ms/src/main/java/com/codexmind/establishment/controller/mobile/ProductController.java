package com.codexmind.establishment.controller.mobile;

import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.usecases.product.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codexmind.establishment.converters.ProductConverter;
import com.codexmind.establishment.dto.ProductDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Set;

@RestController

@RequestMapping("api/v1/product")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    private final SaveProduct saveProduct;

    private final UpdateProduct updateProduct;

    private final DeleteProduct deleteProduct;

    private final DetailProduct detailProduct;

    private final GetAllProducts getAllProducts;

    private final GetProductsByOrder getProductsByOrder;

    private final GetProductsByMenu getProductsByMenu;

    public ProductController(SaveProduct saveProduct, UpdateProduct updateProduct, DeleteProduct deleteProduct, DetailProduct detailProduct, GetAllProducts getAllProducts, GetProductsByOrder getProductsByOrder, GetProductsByMenu getProductsByMenu) {
        this.saveProduct = saveProduct;
        this.updateProduct = updateProduct;
        this.deleteProduct = deleteProduct;
        this.detailProduct = detailProduct;
        this.getAllProducts = getAllProducts;
        this.getProductsByOrder = getProductsByOrder;
        this.getProductsByMenu = getProductsByMenu;
    }


    @PostMapping("/save")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO, Integer idMenu, UriComponentsBuilder uriBuilder) {
        var product = saveProduct.execute(productDTO, idMenu);
        var uri = uriBuilder.path("/save/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(ProductConverter.toDTO(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable Integer id,
            @RequestBody ProductDTO productDTO,
            UriComponentsBuilder uriBuilder) {

        var product = updateProduct.execute(id, productDTO);
        var uri = uriBuilder.path("/update/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.ok().body(ProductConverter.toDTO(product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable Integer id) {
        deleteProduct.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> detailMenu(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ProductConverter.toDTO(detailProduct.execute(id)));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Set<ProductDTO>> getByOrderId(@PathVariable Integer orderId) {
        Set<Product> products = getProductsByOrder.execute(orderId);
        return ResponseEntity.ok().body(ProductConverter.toDTOSet(products));
    }


    @GetMapping(value = "/{id}/list")
    public ResponseEntity<Page<ProductDTO>> getAllProducts
            (@PathVariable Integer id,
             @RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPge,
             @RequestParam(value = "order", defaultValue = "name") String orderBy,
             @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        var list = getAllProducts.execute(
                id,
                page,
                linesPerPge,
                orderBy,
                direction
        ).map(ProductConverter::toDTO);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/menu/{id}/list")
    public ResponseEntity<Set<ProductDTO>> getALlProductsByMenu(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ProductConverter.toDTOSet(getProductsByMenu.execute(id)));
    }
}
