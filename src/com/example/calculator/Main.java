package com.example.calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.print("\n명령을 입력하세요 (연산 / history / clear / removefirst / exit): ");
            String command = scanner.next().toLowerCase();

            if (command.equals("exit")) {
                break;
            } else if (command.equals("history")) {
                ArrayList<String> history = new ArrayList<>(calculator.getResultsHistory());
                System.out.println("\n현재까지의 연산 기록:");
                if (history.isEmpty()) {
                    System.out.println("기록이 없습니다.");
                } else {
                    for (String record : history) {
                        System.out.println(record);
                    }
                }
            } else if (command.equals("clear")) {
                calculator.clearHistory();
                System.out.println("연산 기록이 초기화되었습니다.");
            } else if (command.equals("removefirst")) {
                calculator.removeFirstHistory();
            } else {
                int numberA = 0, numberB = 0;
                char operator;
                try {
                    numberA = Integer.parseInt(command);
                    System.out.print("연산자를 입력하세요 (+, -, *, /): ");
                    operator = scanner.next().charAt(0);

                    if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
                        System.out.println("잘못된 연산자입니다.");
                        continue;
                    }

                    System.out.print("두 번째 숫자를 입력하세요: ");
                    numberB = scanner.nextInt();

                } catch (NumberFormatException e) {
                    System.out.println("잘못된 입력입니다. 올바른 숫자 또는 명령어를 입력하세요.");
                    continue;
                } catch (Exception e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                    scanner.next(); // 입력 버퍼 클리어
                    continue;
                }

                try {
                    double result = calculator.calculate(numberA, numberB, operator);
                    System.out.println("결과: " + result);
                } catch (ArithmeticException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("계산기를 종료합니다.");
        scanner.close();
    }
}
