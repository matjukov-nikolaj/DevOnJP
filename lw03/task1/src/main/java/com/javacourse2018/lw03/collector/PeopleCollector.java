package com.javacourse2018.lw03.collector;

import com.javacourse2018.lw03.model.basket.BasketImpl;
import com.javacourse2018.lw03.model.customer.Customer;
import com.javacourse2018.lw03.model.customer.CustomerImpl;
import com.javacourse2018.lw03.model.customer.CustomerType;
import com.javacourse2018.lw03.model.payment.Method;

import java.math.BigDecimal;

public class PeopleCollector extends BaseCollector<Customer> {

    public PeopleCollector() {
        this.collect();
    }

    protected Integer getId(Customer customer) {
        return customer.getId();
    }

    protected void printEntityInformation(Customer customer) {
        LOG.info("Created a customer: " + customer.getId());
    }

    protected void fillEntityList() {
        Customer customer1 = new CustomerImpl();
        ((CustomerImpl) customer1).setId(0);
        customer1.setBasket(new BasketImpl());
        customer1.setBonuses(new BigDecimal(102));
        customer1.setCardCash(new BigDecimal(0));
        customer1.setCash(new BigDecimal(22350));
        customer1.setPaymentMethod(Method.CASH);
        customer1.setType(CustomerType.ADULT);
        this.entityList.add(customer1);


        Customer customer2 = new CustomerImpl();
        ((CustomerImpl) customer2).setId(1);
        customer2.setBasket(new BasketImpl());
        customer2.setBonuses(new BigDecimal(0));
        customer2.setCardCash(new BigDecimal(100012));
        customer2.setCash(new BigDecimal(150));
        customer2.setPaymentMethod(Method.CARD);
        customer2.setType(CustomerType.ADULT);
        this.entityList.add(customer2);

        Customer customer3 = new CustomerImpl();
        ((CustomerImpl) customer3).setId(2);
        customer3.setBasket(new BasketImpl());
        customer3.setBonuses(new BigDecimal(0));
        customer3.setCardCash(new BigDecimal(0));
        customer3.setCash(new BigDecimal(53100));
        customer3.setPaymentMethod(Method.CASH);
        customer3.setType(CustomerType.CHILD);
        this.entityList.add(customer3);

        Customer customer4 = new CustomerImpl();
        ((CustomerImpl) customer4).setId(3);
        customer4.setBasket(new BasketImpl());
        customer4.setBonuses(new BigDecimal(10020));
        customer4.setCardCash(new BigDecimal(0));
        customer4.setCash(new BigDecimal(500));
        customer4.setPaymentMethod(Method.BONUSES);
        customer4.setType(CustomerType.RETIREE);
        this.entityList.add(customer4);

        Customer customer5 = new CustomerImpl();
        ((CustomerImpl) customer5).setId(4);
        customer5.setBasket(new BasketImpl());
        customer5.setBonuses(new BigDecimal(100));
        customer5.setCardCash(new BigDecimal(112500));
        customer5.setCash(new BigDecimal(500));
        customer5.setPaymentMethod(Method.CARD);
        customer5.setType(CustomerType.RETIREE);
        this.entityList.add(customer5);


        Customer customer6 = new CustomerImpl();
        ((CustomerImpl) customer6).setId(5);
        customer6.setBasket(new BasketImpl());
        customer6.setBonuses(new BigDecimal(100));
        customer6.setCardCash(new BigDecimal(11500));
        customer6.setCash(new BigDecimal(500));
        customer6.setPaymentMethod(Method.CARD);
        customer6.setType(CustomerType.RETIREE);
        this.entityList.add(customer6);


        Customer customer7 = new CustomerImpl();
        ((CustomerImpl) customer7).setId(6);
        customer7.setBasket(new BasketImpl());
        customer7.setBonuses(new BigDecimal(100));
        customer7.setCardCash(new BigDecimal(1500));
        customer7.setCash(new BigDecimal(20300));
        customer7.setPaymentMethod(Method.CASH);
        customer7.setType(CustomerType.ADULT);
        this.entityList.add(customer7);

        Customer customer8 = new CustomerImpl();
        ((CustomerImpl) customer8).setId(7);
        customer8.setBasket(new BasketImpl());
        customer8.setBonuses(new BigDecimal(0));
        customer8.setCardCash(new BigDecimal(5090));
        customer8.setCash(new BigDecimal(0));
        customer8.setPaymentMethod(Method.CARD);
        customer8.setType(CustomerType.ADULT);
        this.entityList.add(customer8);

        Customer customer9 = new CustomerImpl();
        ((CustomerImpl) customer9).setId(8);
        customer9.setBasket(new BasketImpl());
        customer9.setBonuses(new BigDecimal(0));
        customer9.setCardCash(new BigDecimal(12500));
        customer9.setCash(new BigDecimal(0));
        customer9.setPaymentMethod(Method.CARD);
        customer9.setType(CustomerType.ADULT);
        this.entityList.add(customer9);

        Customer customer10 = new CustomerImpl();
        ((CustomerImpl) customer10).setId(9);
        customer10.setBasket(new BasketImpl());
        customer10.setBonuses(new BigDecimal(0));
        customer10.setCardCash(new BigDecimal(0));
        customer10.setCash(new BigDecimal(19000));
        customer10.setPaymentMethod(Method.CASH);
        customer10.setType(CustomerType.CHILD);
        this.entityList.add(customer10);

    }

}
