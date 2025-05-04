package com.icement.api.iCement.Domains.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;

import com.icement.api.iCement.Domains.Shared.Repositories.BaseRepository;

public class ProductRepository extends BaseRepository<Product> {
    ProductRepository() {
        super(Product.class, "products");
    }

    public Optional<Product> findByProductNumber(String productNumber) {
        return findByColumnName("product_number", productNumber);
    }

    public Optional<Product> findLatestByProductNumber(String productNumber) {
        Criteria criteria = Criteria.where("product_number").is(productNumber).and("deleted_at").is(null)
                .and("current").is(true);

        return findOneWithCriteria(criteria);
    }

    public Optional<Product> findByProductNumberAndVersion(String productNumber, Integer version) {
        Criteria criteria = Criteria.where("product_number").is(productNumber).and("version").is(version)
                .and("deleted_at").is(null);

        return findOneWithCriteria(criteria);
    }

    public Optional<List<Product>> findByProductNumbers(List<String> productNumbers) {
        Criteria criteria = Criteria.where("product_number").in(productNumbers).and("current").is(true)
                .and("deleted_at").is(null);

        return findWithCriteria(criteria);
    }

}
