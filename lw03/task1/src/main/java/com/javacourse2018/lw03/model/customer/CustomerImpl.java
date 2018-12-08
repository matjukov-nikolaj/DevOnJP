package com.javacourse2018.lw03.model.customer;

import com.javacourse2018.lw03.model.BaseModel;
import com.javacourse2018.lw03.model.basket.Basket;
import com.javacourse2018.lw03.model.payment.Method;

import java.math.BigDecimal;

public class CustomerImpl extends BaseModel implements Customer {

    private CustomerType type;
    private Basket basket;
    private Method paymentMethod;
    private BigDecimal cash;
    private BigDecimal cardCash;
    private BigDecimal bonuses;

    public Basket getBasket() {
        return basket;
    }

    public CustomerType getType() {
        return type;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Method getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getCardCash() {
        return cardCash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void setPaymentMethod(Method paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCardCash(BigDecimal cardCash) {
        this.cardCash = cardCash;
    }

    public BigDecimal getBonuses() {
        return bonuses;
    }

    public void setBonuses(BigDecimal bonuses) {
        this.bonuses = bonuses;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

}
