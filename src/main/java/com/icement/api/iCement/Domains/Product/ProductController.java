package com.icement.api.iCement.Domains.Product;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Iterable<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public Product create(Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/update")
    public Product update(Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/{productNumber}")
    public Product getByProductNumber(@PathVariable String productNumber, @Param("version") Integer version) {
        if (version != null) {
            return productService.getProductByProductNumberAndVersion(productNumber, version);
        }
        return productService.getLatestProductByProductNumber(productNumber);
    }
}
