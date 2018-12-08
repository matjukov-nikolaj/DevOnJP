package com.javacourse2018.lw03.model.customer;

import com.javacourse2018.lw03.model.basket.Basket;
import com.javacourse2018.lw03.model.payment.Method;

import java.math.BigDecimal;

public interface Customer {

    CustomerType getType();

    void setType(CustomerType type);

    Basket getBasket();

    void setBasket(Basket basket);

    Method getPaymentMethod();

    void setPaymentMethod(Method method);

    BigDecimal getCash();

    void setCash(BigDecimal cash);

    BigDecimal getCardCash();

    void setCardCash(BigDecimal cardCash);

    BigDecimal getBonuses();

    void setBonuses(BigDecimal bonuses);


}
