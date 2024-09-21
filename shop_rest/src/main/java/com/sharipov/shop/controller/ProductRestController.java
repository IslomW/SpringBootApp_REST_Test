package com.sharipov.shop.controller;


import com.sharipov.shop.controller.payload.UpdateProductPayload;
import com.sharipov.shop.model.Product;
import com.sharipov.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-api/products/{productId:\\d+}")
public class ProductRestController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product getProduct(@PathVariable("productId") int productId){
        return this.productService.findProduct(productId)
                .orElseThrow( ()-> new NoSuchElementException("Not found product"));    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product")Product product){
        return product;
    }


    @PatchMapping
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") int productId,
                                              @RequestBody UpdateProductPayload payload){
        this.productService.updateProduct(productId, payload.title(), payload.details());
        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId){
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent()
                .build();

    }
}
