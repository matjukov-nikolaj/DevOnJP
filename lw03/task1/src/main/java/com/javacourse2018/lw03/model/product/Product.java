package com.javacourse2018.lw03.model.product;

import java.math.BigDecimal;

public interface Product {

    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    void setDiscount(Integer discount);

    Integer getDiscount();

    Boolean isAdult();

    void setAdult(Boolean isAdult);

    Measure getMeasure();

    void setMeasure(Measure measure);

    Integer getAmount();

    void setAmount(Integer amount);

}
