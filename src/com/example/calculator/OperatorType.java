package com.example.calculator;

public enum OperatorType {
    ADD('+', (a, b) -> a + b),
    SUBTRACT('-', (a, b) -> a - b),
    MULTIPLY('*', (a, b) -> a * b),
    DIVIDE('/', (a, b) -> {
        if (b == 0) {
            throw new ArithmeticException("나눗셈의 분모는 0이 될 수 없습니다.");
        }
        return (double) a / b;
    });

    private final char symbol;
    private final Operation operation;

    OperatorType(char symbol, Operation operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public char getSymbol() {
        return symbol;
    }

    public double apply(int a, int b) {
        return operation.apply(a, b);
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
