package com.javacourse2018.lw03.model.basket;

import com.javacourse2018.lw03.model.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Basket {

    void addProduct(Product product);

    void removeProduct(Product product) throws IndexOutOfBoundsException;

    void clear();

    Integer size();

    Boolean isEmpty();

    void incrementBasketCost(BigDecimal cost);

    BigDecimal getBasketCost();

    Map<Integer, Product> getProducts();

    void setProducts(Map<Integer, Product> products);

}
