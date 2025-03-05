package com.example.calculator;

public enum OperatorType {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;

    OperatorType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static OperatorType fromChar(char ch) {
        for (OperatorType op : values()) {
            if (op.symbol == ch) {
                return op;
            }
        }
        throw new IllegalArgumentException("잘못된 연산자입니다.");
    }
}
