package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.basket.Basket;
import com.javacourse2018.lw03.model.customer.Customer;
import com.javacourse2018.lw03.model.customer.CustomerType;
import com.javacourse2018.lw03.model.product.Product;
import com.javacourse2018.lw03.model.report.Report;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class CashDeskImpl implements CashDesk {
    private static final Log LOG = LogFactory.getLog(CashDeskImpl.class);

    private Queue<Customer> queue = new LinkedList<>();

    private Map<Integer, Product> store = new TreeMap<>();

    private Report report;

    public CashDeskImpl(Map<Integer, Product> store, Report report) {
        this.store = store;
        this.report = report;
    }

    public void addCustomerToQueue(Customer customer) {
        this.queue.add(customer);
    }

    public void removeCustomerFromQueue() {
        this.queue.remove();
    }

    private Customer getFirstCustomerInQueue() {
        return this.queue.peek();
    }

    public void serve() {
        if (this.queue.isEmpty()) {
            return;
        }

        Customer customer = this.getFirstCustomerInQueue();
        Basket basket = customer.getBasket();

        if (basket.isEmpty()) {
            LOG.info("The customer #" + customer.getId() + " has not bought anything");
            this.removeCustomerFromQueue();
            return;
        }

        BigDecimal productsCost = this.getProductsCost(basket.getProducts(), customer);

        LOG.info("---> Basket total price: " + productsCost);

        this.payment(productsCost, customer, basket);

        this.removeCustomerFromQueue();

        LOG.info("  Customer left the store");
    }

    private BigDecimal getProductsCost(Map<Integer, Product> products, Customer customer) {
        BigDecimal result = new BigDecimal(0);

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Integer key = entry.getKey();
            Product product = products.get(key);
            if (product.isAdult() && (customer.getType() == CustomerType.CHILD)) {
                this.returnProduct(product);
                continue;
            }
            LOG.info("---Customer buy product---");
            LOG.info("  Name: " + product.getName());
            LOG.info("  Amount: " + product.getAmount());
            BigDecimal price = product.getPrice().multiply(new BigDecimal(product.getAmount()));
            result = result.add(price);
        }

        return result;
    }

    private void returnProduct(Product product) {
        LOG.info("Return product to store: " + product.getName());
        Product returnedProduct = this.store.get(product.getId());
        returnedProduct.setAmount(returnedProduct.getAmount() + product.getAmount());
    }

    private void payment(BigDecimal totalCost, Customer customer, Basket basket) {
        switch (customer.getPaymentMethod()) {
            case CARD:
                this.payByCard(customer, totalCost, basket);
                break;
            case CASH:
                payByCash(customer, totalCost, basket);
                break;
            case BONUSES:
                payByBonuses(customer, totalCost, basket);
                break;
        }
    }

    private void addPaymentsProductToReport(Map<Integer, Product> products) {
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            this.report.addProductToSoldProducts(entry.getValue());
        }
    }

    private void payByBonuses(Customer customer, BigDecimal totalCost, Basket basket) {
        if (customer.getBonuses().compareTo(totalCost) >= 0) {
            LOG.info("The customer #" + customer.getId() + " pay: " + totalCost);
            customer.setBonuses(customer.getBonuses().subtract(totalCost));
            this.addPaymentsProductToReport(basket.getProducts());
            LOG.info("  Payment complete");
        } else {
            this.returnAllProductsInBasketToStore(customer, basket);
        }
    }

    private void payByCash(Customer customer, BigDecimal totalCost, Basket basket) {
        if (customer.getCash().compareTo(totalCost) >= 0) {
            LOG.info("The customer #" + customer.getId() + " pay: " + totalCost);
            customer.setCash(customer.getCash().subtract(totalCost));
            this.addPaymentsProductToReport(basket.getProducts());
            LOG.info("  Payment complete");
        } else {
            this.returnAllProductsInBasketToStore(customer, basket);
        }
    }

    private void payByCard(Customer customer, BigDecimal totalCost, Basket basket) {
        if (customer.getCardCash().compareTo(totalCost) >= 0) {
            LOG.info("The customer #" + customer.getId() + " pay: " + totalCost);
            customer.setCardCash(customer.getCardCash().subtract(totalCost));
            this.addPaymentsProductToReport(basket.getProducts());
            LOG.info("  Payment complete");
        } else {
            this.returnAllProductsInBasketToStore(customer, basket);
        }
    }

    private void returnAllProductsInBasketToStore(Customer customer, Basket basket) {
        LOG.error("The customer #" + customer.getId() + " not enough money");
        Map<Integer, Product> customerProducts = basket.getProducts();
        for (Map.Entry<Integer, Product> entry: customerProducts.entrySet()) {
            Integer key = entry.getKey();
            Product product = customerProducts.get(key);
            this.returnProduct(product);
        }
        LOG.info("  Products returned to the store.");
    }


}
