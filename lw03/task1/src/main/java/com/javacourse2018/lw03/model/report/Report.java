package com.javacourse2018.lw03.model.report;

import com.javacourse2018.lw03.model.product.Product;

import java.util.Map;

public interface Report {
    void incrementNumberOfCustomers();

    Map<Integer, Product> getSoldProducts();

    void setSoldProducts(Map<Integer, Product> soldProducts);

    void addProductToSoldProducts(Product product);

    void printReport();

}
