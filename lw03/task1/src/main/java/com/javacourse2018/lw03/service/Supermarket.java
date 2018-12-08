package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.collector.ProductCollector;
import com.javacourse2018.lw03.collector.ProductCollectorImpl;
import com.javacourse2018.lw03.model.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {

    private boolean isOpen;
    private List<Customer> customers = new ArrayList<>();
    private ProductCollector productCollector = new ProductCollectorImpl();

    private List<CashDesk> cashDesks = new ArrayList<>();

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }

    

}
