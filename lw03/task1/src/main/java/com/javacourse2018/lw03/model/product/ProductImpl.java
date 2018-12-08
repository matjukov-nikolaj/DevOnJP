package com.javacourse2018.lw03.model.product;

import com.javacourse2018.lw03.model.BaseModel;

import java.math.BigDecimal;

public class ProductImpl extends BaseModel implements Product {

    private Integer amount;
    private String name;
    private Measure measure;
    private BigDecimal price;
    private Integer discount;
    private Boolean isAdult;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean isAdult() {
        return this.isAdult;
    }

    public void setAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
