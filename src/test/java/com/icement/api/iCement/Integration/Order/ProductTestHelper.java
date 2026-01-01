package com.icement.api.iCement.Integration.Order;

import com.icement.api.iCement.product.Product;
import com.icement.api.iCement.product.repository.ProductRepository;

public class ProductTestHelper {

    private final ProductRepository productRepository;

    public ProductTestHelper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

        public void createProduct() throws Exception {
        Product product = Product.builder()
                .name("Cement")
                .description("High quality cement")
                .price(50.0)
                .version(1)
                .productNumber("productNumber123")
                .imageUrl("http://example.com/image.jpg")
                .categoryId("categoryId")
                .isActive(true)
                .isNew(true)
                .current(true)
                .build();

        productRepository.save(product);
    }
}
