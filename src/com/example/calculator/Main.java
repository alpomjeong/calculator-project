package com.example.calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        while (true) {
            System.out.print("\n명령을 입력하세요 (연산 / history / clear / removefirst / exit): ");
            String command = scanner.next().toLowerCase();

            if (command.equals("exit")) {
                break;
            } else if (command.equals("history")) {
                ArrayList<String> history = calculator.getResultsHistory();
                System.out.println("\n[연산 기록]");
                if (history.isEmpty()) {
                    System.out.println("기록이 없습니다.");
                } else {
                    for (String record : history) {
                        System.out.println(record);
                    }
                }
            } else if (command.equals("clear")) {
                calculator.clearHistory();
            } else if (command.equals("removefirst")) {
                calculator.removeFirstHistory();
            } else {
                int numberA, numberB;
                OperatorType operator;

                try {
                    numberA = Integer.parseInt(command);
                    System.out.print("연산자를 입력하세요 (+, -, *, /): ");
                    char operatorChar = scanner.next().charAt(0);
                    operator = OperatorType.fromChar(operatorChar);

                    System.out.print("두 번째 숫자를 입력하세요: ");
                    numberB = scanner.nextInt();

                } catch (NumberFormatException e) {
                    System.out.println("잘못된 입력입니다. 올바른 숫자 또는 명령어를 입력하세요.");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                try {
                    double result = calculator.calculate(numberA, numberB, operator);
                    System.out.println("결과: " + result);
                } catch (ArithmeticException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("계산기를 종료합니다.");
        scanner.close();
    }
}
