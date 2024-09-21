package com.sharipov.shop.controller;

import com.sharipov.shop.controller.payload.NewProductPayload;
import com.sharipov.shop.model.Product;
import com.sharipov.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-api/products")
public class ProductsRestController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findProducts() {
        return this.productService.findAllProducts();
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody NewProductPayload payload,
                                                 UriComponentsBuilder uriComponentsBuilder) {
        Product product = this.productService.createProduct(payload.title(), payload.details());
        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/catalog-api/products/{productId}")
                        .build(Map.of("productId", product.getId())))

                .body(product);
    }


}
