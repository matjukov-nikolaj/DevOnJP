package com.javacourse2018.lw03.collector;

import com.javacourse2018.lw03.model.product.Product;

import java.util.Map;

public interface ProductCollector {

    Map<Integer, Product> getProducts();

    void setProducts(Map<Integer, Product> products);

    Product getProduct(Integer id) throws IndexOutOfBoundsException;

    void removeProduct(Integer id) throws IndexOutOfBoundsException;

    Integer getProductsSize();

}
