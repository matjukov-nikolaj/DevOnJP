package com.devonjp;

import java.util.ArrayList;

public class ParametersController {

    private String[] args;

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() throws IllegalArgumentException {
        if (this.args.length != 2) {
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<Byte> getIpAddress() throws IllegalArgumentException {
        String ipAddress = this.args[0];
        return this.getByteArray(ipAddress);
    }

    public ArrayList<Byte> getSubnetMask() throws IllegalArgumentException {
        String subnetMask = this.args[1];
        return this.getByteArray(subnetMask);
    }

    private ArrayList<Byte> getByteArray(String str) throws IllegalArgumentException {
        String[] numbers = str.split("[.]");
        if (numbers.length != 3) {
            throw new IllegalArgumentException();
        }
        ArrayList<Byte> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; ++i) {
            String subStr = numbers[i];
            int number = Integer.parseInt(subStr);
            if (this.isValidNumber(number)) {
                byte byteNumber = (byte) number;
                result.add(byteNumber);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    private Boolean isValidNumber(Integer number) {
        return (number >= 0 && number <= 255);
    }

}
