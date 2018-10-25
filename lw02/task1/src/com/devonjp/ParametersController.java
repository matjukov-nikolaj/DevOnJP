package com.devonjp;
import java.util.ArrayList;

public class ParametersController {

    private static final String WRONG_MASK = "Wrong mask";
    private static final String WRONG_IP = "Wrong ip address";

    private String[] args;

    public ParametersController(String[] args) {
        this.args = args;
        checkTheNumberOfParameters();
    }

    public ArrayList<Byte> getIpAddress() {
        try {
            String ipAddress = this.args[0];
            return this.getByteArray(ipAddress);
        } catch (Exception e) {
            System.out.println(WRONG_IP);
        }
        return null;
    }

    public ArrayList<Byte> getSubnetMask() {
        try {
            String subnetMask = this.args[1];
            ArrayList<Byte> mask = this.getByteArray(subnetMask);
            this.validationMask(mask);
            return mask;
        } catch (Exception e) {
            System.out.println(WRONG_MASK);
        }
        return null;
    }

    private void checkTheNumberOfParameters() {
        if (this.args.length != 2) {
            throw new IllegalArgumentException();
        }
    }

    private ArrayList<Byte> getByteArray(String str) throws IllegalArgumentException, NumberFormatException {
        ArrayList<Byte> result = new ArrayList<>();
        String[] numbers = str.split("\\.");
        Integer numberOfDots = str.length()  - str.replaceAll("[.]+", "").length();
        if (numbers.length != 4  || numberOfDots != 3) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numbers.length; ++i) {
            String subStr = numbers[i];
            if (!subStr.matches("\\d+")) {
                throw new IllegalArgumentException();
            }
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

    private void validationMask(ArrayList<Byte> result) throws IllegalArgumentException {
        Boolean isValidMask = true;
        for (int i = 0; i < result.size(); ++i) {
            String binaryStr = String.format("%8s", Integer.toBinaryString(result.get(i) & 0xFF)).replace(' ', '0');
            char[] symbols = binaryStr.toCharArray();
            Character prevCh = symbols[0];
            for (int j = 1; j < symbols.length; ++j) {
                Character currCh = symbols[j];
                if (prevCh == '0' && currCh == '1') {
                    isValidMask = false;
                    break;
                }
                prevCh = currCh;
            }
        }
        if (!isValidMask) {
            throw new IllegalArgumentException();
        }
    }


    private Boolean isValidNumber(Integer number) {
        return (number >= 0 && number <= 255);
    }

}

