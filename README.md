# 과제 소개
>
> * 본 과제는 자바의 기능들을 최대한 활용하여 사용자의 입력을 받아 기초적인 사칙연산과 연산결과 조회 등이 가능하도록 한 프로젝트입니다.

# 과제 구조 
> 
> * README.md : 프로젝트 설명서
> * Operation.java : 람다식 인터페이스
> * OperatorType.java : Enum을 활용한 연산 정의
> * ArithmeticCalculator.java : 연산 수행 및 기록 관리
> * Main.java : 사용자 입력 처리 및 실행

# 과제 상세 기능
>
>  ## 1. Operation.java
> ```java
>  public interface Operation{
>       double apply(int a, int b);
> }
> ```
> 단일 메서드를 가진 함수형 인터페이스로 람다식 활용을 위해 작성했습니다.
> ## 2. OperatorType.java
>```java
>public enum OperatorType{
>   ADD('+',(a,b)->a+b),
>   ...
>   DIVIDE('/',(a,b)->{
>       if(b=0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
>       return (double)a/b;
>   });
>```
> 연산자를 enum을 통해 람다식과 함께 정의합니다.
> ```java
> OperatorType(char symbol, Operation operation) {
>   this.symbol = symbol;
>   this.operation = operation;
> }
> ```
> OperatorType의 생성자로 각 연산자는 연산 기호와 람다식을 저장합니다
>```java
>public double apply(int a, int b) {
>   return operation.apply(a, b);
> }
> ```
> apply 메서드는 람다식을 실행 시킵니다.
> ```java
> public static OperatorType fromChar(char ch) {
>    for (OperatorType op : values()) {
>        if (op.symbol == ch) {
>            return op;
>        }
>    }
>    throw new IllegalArgumentException("잘못된 연산자입니다.");
> }
> ```
> char로 입력된 사칙연산 기호들을 Enum으로 변환하고 연산자가 아닌것이 입력되면 예외를 발생시킵니다.
> 
>## 3. ArithmeticCalculator.java
> ```java
>  private ArrayList<String> resultsHistory = new ArrayList<>();
> ```
> 연산 기록들을 저장하는 컬렉션 ArrayList 입니다.
>```java
> public double calculate(int a, int b, OperatorType operator) {
>   double result = operator.apply(a, b);
>   resultsHistory.add(a + " " + operator.getSymbol() + " " + b + " = " + result);
>   return result;
> }
>```
> OperatorType.java 에서 작성한 operator.apply(a,b);를 호출하여 연산을 수행한 후 위의 resultsHistory 리스트에 결과를 저장합니다.
> ```java
>   public ArrayList<String> getResultsHistory() {
>        return new ArrayList<>(resultsHistory);
>    }
>
>    public void clearHistory() {
>        resultsHistory.clear();
>        System.out.println("연산 기록이 초기화되었습니다.");
>    }
>
>    public void removeFirstHistory() {
>        if (!resultsHistory.isEmpty()) {
>            resultsHistory.remove(0);
>            System.out.println("가장 먼저 추가된 기록이 삭제되었습니다.");
>        } else {
>            System.out.println("삭제할 기록이 없습니다.");
>        }
>    }
> ```
> 위 세개의 함수는 각각 연산기록을 반환, 모든 연산기록 삭제, 가장 먼저 리스트에 추가된 연산 기록 삭제(FIFO)를 구현한 부분입니다.
> ## 4. Main.java
> ```java
> package com.example.calculator;
>
>import java.util.ArrayList;
>import java.util.Scanner;
>
>public class Main {
>public static void main(String[] args) {
>Scanner scanner = new Scanner(System.in);
>ArithmeticCalculator calculator = new ArithmeticCalculator();
>
>        while (true) {
>            System.out.print("\n명령을 입력하세요 (연산 / history / clear / removefirst / exit): ");
>            String command = scanner.next().toLowerCase();
>
>            if (command.equals("exit")) {
>                break;
>            } else if (command.equals("history")) {
>                ArrayList<String> history = calculator.getResultsHistory();
>                System.out.println("\n[연산 기록]");
>                if (history.isEmpty()) {
>                    System.out.println("기록이 없습니다.");
>                } else {
>                    for (String record : history) {
>                        System.out.println(record);
>                    }
>                }
>            } else if (command.equals("clear")) {
>                calculator.clearHistory();
>            } else if (command.equals("removefirst")) {
>                calculator.removeFirstHistory();
>            } else {
>                int numberA, numberB;
>                OperatorType operator;
>
>                try {
>                    numberA = Integer.parseInt(command);
>                    System.out.print("연산자를 입력하세요 (+, -, *, /): ");
>                    char operatorChar = scanner.next().charAt(0);
>                    operator = OperatorType.fromChar(operatorChar);
>
>                    System.out.print("두 번째 숫자를 입력하세요: ");
>                    numberB = scanner.nextInt();
>
>                } catch (NumberFormatException | IllegalArgumentException e) {
>                    System.out.println("잘못된 입력입니다.");
>                    continue;
>                }
>
>                try {
>                    double result = calculator.calculate(numberA, numberB, operator);
>                    System.out.println("결과: " + result);
>                } catch (ArithmeticException e) {
>                    System.out.println(e.getMessage());
>                }
>            }
>        }
>
>        System.out.println("계산기를 종료합니다.");
>        scanner.close();
>    }
> }
> ```
>  Scanner 사용자 입력을 통해 조건문에 따라 해당된 동작을 하도록 구현된 메인 클래스 OperatorType.fromChar를 통해 연산자를 enum형식으로 받고 Arithmetic Calculator를 호출해 연산 수행후 결과를 출력합니다. 