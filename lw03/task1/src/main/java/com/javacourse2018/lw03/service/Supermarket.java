package com.javacourse2018.lw03.service;

import com.javacourse2018.lw03.collector.PeopleCollector;
import com.javacourse2018.lw03.collector.ProductCollector;
import com.javacourse2018.lw03.config.Constants;
import com.javacourse2018.lw03.model.customer.Customer;
import com.javacourse2018.lw03.model.customer.CustomerImpl;
import com.javacourse2018.lw03.model.customer.CustomerState;
import com.javacourse2018.lw03.model.payment.Method;
import com.javacourse2018.lw03.model.product.Product;
import com.javacourse2018.lw03.model.product.ProductImpl;
import com.javacourse2018.lw03.model.report.Report;
import com.javacourse2018.lw03.model.report.ReportImpl;

import java.math.BigDecimal;
import java.util.*;

public class Supermarket {

    private List<Customer> customers = new ArrayList<>();
    private ProductCollector productCollector = new ProductCollector();
    private PeopleCollector peopleCollector = new PeopleCollector();

    private List<CashDeskImpl> cashDesks = new ArrayList<>();
    private Report report = new ReportImpl();
    private Integer workingTime;

    private Random random = new Random();

    public Supermarket(Integer workingTime) {
        this.workingTime = workingTime;
        this.initializeCashDesk();
    }

    private void initializeCashDesk() {
        for (int i = 0; i < Constants.CASH_DESK_SIZE; ++i) {
            cashDesks.add(new CashDeskImpl(productCollector.getEntities(), report));
        }
    }

    public void start() {
        while (this.workingTime != 0) {
            if (this.isNewCustomer()) {
                this.addNewCustomer(this.random.nextInt((this.peopleCollector.size() - 1)));
                this.report.incrementNumberOfCustomers();
            }
            this.customersHandler();
            this.cashDesksHandler();
            this.workingTime--;
        }
        this.report.printReport();
    }

    private void customersHandler() {
        for (Customer customer : this.customers) {
            if (customer.getState() == CustomerState.BUY) {
                CashDeskImpl cashDeskImpl = this.getRandomCashDesk();
                cashDeskImpl.addCustomerToQueue(customer);
                continue;
            }
            CustomerState state = CustomerState.getRandom();
            if (state == CustomerState.SELECT_PRODUCT) {
                Product product = getRandomProduct();
                BigDecimal productCost = product.getPrice().multiply(new BigDecimal(product.getAmount()));
                BigDecimal expectedCost = customer.getBasket().getBasketCost().add(productCost);
                if (expectedCost.compareTo(this.getCustomerCashForPaymentMethod(customer)) < 0) {
                    customer.getBasket().addProduct(product);
                    customer.getBasket().incrementBasketCost(expectedCost);
                    Product storeProduct = this.productCollector.getEntities().get(product.getId());
                    storeProduct.setAmount(storeProduct.getAmount() - product.getAmount());
                } else {
                    customer.setState(CustomerState.BUY);
                }
            }
            if (state == CustomerState.BUY) {
                customer.setState(state);
            }
        }
    }

    private void cashDesksHandler() {
        for (CashDeskImpl cashDeskImpl : this.cashDesks) {
            cashDeskImpl.serve();
        }
    }

    private boolean isNewCustomer() {
        return random.nextBoolean();
    }

    private BigDecimal getCustomerCashForPaymentMethod(Customer customer) {
        Method method = customer.getPaymentMethod();
        if (method == Method.CASH) {
            return customer.getCash();
        }
        if (method == Method.CARD) {
            return customer.getCardCash();
        }
        return customer.getBonuses();
    }

    private Product getRandomProduct() {
        Integer length = this.productCollector.size();
        Integer random = this.random.nextInt(length);
        Product product = this.productCollector.getEntity(random);
        Product newProduct = new ProductImpl();
        newProduct.setId(product.getId());
        newProduct.setPrice(product.getPrice());
        newProduct.setAdult(product.isAdult());
        newProduct.setDiscount(product.getDiscount());
        newProduct.setMeasure(product.getMeasure());
        newProduct.setName(product.getName());
        Integer amount = this.random.nextInt(Constants.MAX_CUSTOMER_PRODUCT_AMOUNT) + 1;
        if (amount > product.getAmount()) {
            amount = product.getAmount();
            this.productCollector.getEntities().remove(product.getId());
        }
        newProduct.setAmount(amount);
        return newProduct;
    }

    private CashDeskImpl getRandomCashDesk() {
        Integer random = this.random.nextInt((Constants.CASH_DESK_SIZE - 1));
        return this.cashDesks.get(random);
    }

    private void addNewCustomer(Integer value) {
        Customer customer = this.peopleCollector.getEntity(value);
        Customer newCustomer = new CustomerImpl();
        newCustomer.setId(customer.getId());
        newCustomer.setBonuses(customer.getBonuses());
        newCustomer.setCash(customer.getCash());
        newCustomer.setCardCash(customer.getCardCash());
        newCustomer.setType(customer.getType());
        newCustomer.setPaymentMethod(customer.getPaymentMethod());
        newCustomer.setBasket(customer.getBasket());
        newCustomer.setState(CustomerState.SELECT_PRODUCT);
        this.customers.add(newCustomer);
    }

}
