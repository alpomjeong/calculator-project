package com.example.calculator;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<String> resultsHistory = new ArrayList<>(); // ✅ ArrayList 직접 사용

    public double calculate(int a, int b, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("나눗셈의 분모는 0이 될 수 없습니다.");
                }
                result = (double) a / b;
                break;
            default:
                throw new IllegalArgumentException("잘못된 연산자입니다.");
        }
        resultsHistory.add(a + " " + operator + " " + b + " = " + result);
        return result;
    }

    public ArrayList<String> getResultsHistory() {
        return resultsHistory;
    }

    public void setResultsHistory(ArrayList<String> resultsHistory) {
        this.resultsHistory = new ArrayList<>(resultsHistory);
    }

    public void clearHistory() {
        resultsHistory.clear();
    }

    public void removeFirstHistory() {
        if (!resultsHistory.isEmpty()) {
            resultsHistory.remove(0);
            System.out.println("가장 먼저 추가된 기록이 삭제되었습니다.");
        } else {
            System.out.println("삭제할 기록이 없습니다.");
        }
    }

    public void printHistory() {
        System.out.println("\n[연산 기록]");
        if (resultsHistory.isEmpty()) {
            System.out.println("기록이 없습니다.");
        } else {
            for (String record : resultsHistory) {
                System.out.println(record);
            }
        }
    }
}
