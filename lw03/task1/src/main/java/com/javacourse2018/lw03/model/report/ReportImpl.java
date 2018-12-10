package com.javacourse2018.lw03.model.report;

import com.javacourse2018.lw03.model.product.Product;
import com.javacourse2018.lw03.model.product.ProductImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class ReportImpl implements Report {
    private static final Log LOG = LogFactory.getLog(ReportImpl.class);

    private Map<Integer, Product> soldProducts = new TreeMap<>();

    private Integer numberOfCustomers = 0;

    public void incrementNumberOfCustomers() {
        this.numberOfCustomers++;
    }

    public Map<Integer, Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Map<Integer, Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public void addProductToSoldProducts(Product product) {
        if (this.soldProducts.containsKey(product.getId())) {
            Integer newAmount = product.getAmount();
            Product soldProduct = this.soldProducts.get(product.getId());
            soldProduct.setAmount(soldProduct.getAmount() + newAmount);
        } else {
            Product soldProduct = new ProductImpl();
            soldProduct.setAmount(product.getAmount());
            soldProduct.setName(product.getName());
            soldProduct.setMeasure(product.getMeasure());
            soldProduct.setDiscount(product.getDiscount());
            soldProduct.setAdult(product.isAdult());
            soldProduct.setPrice(product.getPrice());
            soldProduct.setId(product.getId());
            this.soldProducts.put(product.getId(), soldProduct);
        }
    }

    public void printReport() {
        this.printReportBanner();
        for (Map.Entry<Integer, Product> entry: this.soldProducts.entrySet()) {
            Integer key = entry.getKey();
            Product product = this.soldProducts.get(key);
            LOG.info("Sold product " + product.getName() + " Amount: " + product.getAmount() + " Price: " + product.getPrice());
            BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(product.getAmount()));
            LOG.info("Proceeds: " + totalPrice);
        }
        LOG.info("Total number of customers: " + this.numberOfCustomers);
    }

    private void printReportBanner() {
        LOG.info("-------------------------------------------");
        LOG.info("                  REPORT                   ");
        LOG.info("-------------------------------------------");
    }

}
