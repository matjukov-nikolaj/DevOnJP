package com.javacourse2018.lw03.model;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char character;

    Operation(final char character) {
        this.character = character;
    }

    public static Operation charsToOperation(Character ch) {
        Map<Character, Operation> operation = new HashMap<>();
        operation.put(ADDITION.character, ADDITION);
        operation.put(SUBTRACTION.character, SUBTRACTION);
        operation.put(MULTIPLICATION.character, MULTIPLICATION);
        operation.put(DIVISION.character, DIVISION);
        return operation.get(ch);
    }

    public static String operationToString(Operation operation) {
        Map<Operation, String> operations = new HashMap<>();
        operations.put(ADDITION, "+");
        operations.put(SUBTRACTION, "-");
        operations.put(MULTIPLICATION, "*");
        operations.put(DIVISION, "/");
        return operations.get(operation);
    }

    public static Operation createFromCharacter(char character) {
        return charsToOperation(character);
    }

}
