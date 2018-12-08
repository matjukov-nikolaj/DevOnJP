package com.javacourse2018.lw03.model.bill;

import java.math.BigDecimal;

public class Bill {

    private BigDecimal bill;

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public void incrementBill(BigDecimal price) {
        this.bill.add(price);
    }

}
