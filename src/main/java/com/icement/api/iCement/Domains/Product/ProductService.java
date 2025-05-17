package com.icement.api.iCement.Domains.Product;

import org.springframework.stereotype.Service;

import com.icement.api.iCement.Exceptions.NotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = getLatestProductByProductNumber(product.getProductNumber());
        Product latestProduct = storeAndReturnLatestProduct(product, existingProduct.getVersion());

        makeExisitingCurrentProductObsolete(existingProduct);
        return latestProduct;
    }

    public Product getLatestProductByProductNumber(String productNumber) {
        return productRepository.findLatestByProductNumber(productNumber)
            .orElseThrow(() -> new NotFoundException("Product with product number " + productNumber + " not found"));
    }

    private Product storeAndReturnLatestProduct(Product product, Integer latestProductVersion) {
        product.setVersion(latestProductVersion + 1);
        return productRepository.save(product);
    }

    private void makeExisitingCurrentProductObsolete(Product existingProduct) {
        existingProduct.setCurrent(false);
        productRepository.save(existingProduct);
    }

    public Product getProductByProductNumberAndVersion(String productNumber, Integer version) {
        return productRepository.findByProductNumberAndVersion(productNumber, version)
            .orElseThrow(() -> new NotFoundException("Product with product number " + productNumber + " and version " + version + " not found"));
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }



}
