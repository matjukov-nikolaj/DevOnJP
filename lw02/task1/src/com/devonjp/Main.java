package com.devonjp;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ParametersController parametersController = new ParametersController(args);
            ArrayList<Byte> ipAddress = parametersController.getIpAddress();
            ArrayList<Byte> subnetMask = parametersController.getSubnetMask();
            printAddress(getNetAddress(ipAddress, subnetMask));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Byte> getNetAddress(ArrayList<Byte> ipAddress, ArrayList<Byte> subnetMask) {
        ArrayList<Byte> netAddress = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            netAddress.add(((byte) (ipAddress.get(i) & subnetMask.get(i))));
        }
        return netAddress;
    }

    private static void printAddress(ArrayList<Byte> bytes) {
        for (int i = 0; i < bytes.size(); i++) {
            if (i != (bytes.size() - 1)) {
                System.out.print(Integer.toString(256 + (int) bytes.get(i) & 0xFF) + ".");
            } else {
                System.out.println(Integer.toString(256 + (int) bytes.get(i) & 0xFF));
            }
        }
        System.out.println();
    }

}