package com.devonjp;

import java.util.ArrayList;

public class ParametersController {

    private static final String INCORRECT_DATA = "Ошибка! Неверный ввод данных.";
    private static final String HELP = "java -jar task1.jar <IP address> <Subnet mask>";

    private String[] args;

    public ParametersController(String[] args){
        this.args = args;
        checkTheNumberOfParameters();
    }

    private void checkTheNumberOfParameters() {
        try {
            if (this.args.length != 2) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException argumentEx) {
            this.printErrorInfo();
        }
    }

    public ArrayList<Byte> getIpAddress() {
        String ipAddress = this.args[0];
        return this.getByteArray(ipAddress);
    }

    public ArrayList<Byte> getSubnetMask() {
        String subnetMask = this.args[1];
        return this.getByteArray(subnetMask);
    }

    private ArrayList<Byte> getByteArray(String str) {
        String[] numbers = str.split("[.]");
        ArrayList<Byte> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; ++i) {
            String subStr = numbers[i];
            int number = Integer.parseInt(subStr);
            byte byteNumber = (byte) number;
            result.add(byteNumber);
        }
        return result;
    }

    private void printErrorInfo() {
        System.out.println(INCORRECT_DATA);
        System.out.println(HELP);
    }
}
