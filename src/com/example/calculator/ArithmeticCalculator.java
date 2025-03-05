package com.example.calculator;

import java.util.ArrayList;

public class ArithmeticCalculator {
    private ArrayList<String> resultsHistory = new ArrayList<>();

    public double calculate(int a, int b, OperatorType operator) {
        double result = operator.apply(a, b);
        resultsHistory.add(a + " " + operator.getSymbol() + " " + b + " = " + result);
        return result;
    }

    public ArrayList<String> getResultsHistory() {
        return new ArrayList<>(resultsHistory);
    }

    public void clearHistory() {
        resultsHistory.clear();
        System.out.println("연산 기록이 초기화되었습니다.");
    }

    public void removeFirstHistory() {
        if (!resultsHistory.isEmpty()) {
            resultsHistory.remove(0);
            System.out.println("가장 먼저 추가된 기록이 삭제되었습니다.");
        } else {
            System.out.println("삭제할 기록이 없습니다.");
        }
    }
}
