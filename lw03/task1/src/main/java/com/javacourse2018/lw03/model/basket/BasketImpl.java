package com.javacourse2018.lw03.model.basket;

import com.javacourse2018.lw03.model.product.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class BasketImpl implements Basket {

    private Map<Integer, Product> products = new TreeMap<>();
    private BigDecimal basketCost = new BigDecimal(0);

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Integer size() {
        return this.products.size();
    }

    public Boolean isEmpty() {
        return this.products.isEmpty();
    }

    public void incrementBasketCost(BigDecimal cost) {
        this.basketCost.add(cost);
    }

    public BigDecimal getBasketCost() {
        return this.basketCost;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.put(product.getId(), product);
    }

    public void removeProduct(Product product) throws IndexOutOfBoundsException {
        if (this.products.containsKey(product.getId())) {
            this.products.remove(product.getId());
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clear() {
        this.products.clear();
    }

}
