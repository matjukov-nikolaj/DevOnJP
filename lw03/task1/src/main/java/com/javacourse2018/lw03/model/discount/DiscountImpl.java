package com.javacourse2018.lw03.model.discount;

public class DiscountImpl {

    private Integer discount;

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public static Boolean isCorrectDiscount(Integer discount) {
        return (discount >= 0 && discount <= 100);
    }
}
