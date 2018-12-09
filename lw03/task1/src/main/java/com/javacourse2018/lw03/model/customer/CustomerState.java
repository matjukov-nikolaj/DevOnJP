package com.javacourse2018.lw03.model.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum CustomerState {
    BUY,
    SELECT_PRODUCT;

    public static CustomerState getRandom() {
        List<CustomerState> states = new ArrayList<>();
        states.add(SELECT_PRODUCT);
        states.add(SELECT_PRODUCT);
        states.add(BUY);
        states.add(SELECT_PRODUCT);
        states.add(SELECT_PRODUCT);
        Integer random = (new Random()).nextInt(states.size() - 1);
        return states.get(random);
    }

}
