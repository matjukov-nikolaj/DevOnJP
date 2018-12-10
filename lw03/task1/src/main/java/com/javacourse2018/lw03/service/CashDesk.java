package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.model.customer.Customer;

public interface CashDesk {
    void addCustomerToQueue(Customer customer);

    void removeCustomerFromQueue();

    void serve();

}
