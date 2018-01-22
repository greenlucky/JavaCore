package com.lamdevops.completionfuture.tutorial1;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ProductService {

    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        IntStream.range(1, 1000).forEach(i -> products.add(new Product(Long.valueOf(i), "Product " + i)));

    }


    public Product getProductDetail(final long id) {
        Optional<Product>  product = products.stream().filter(prod -> prod.getId() == id).findFirst();
        return product.orElse(null);
    }
}
