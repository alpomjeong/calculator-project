package backup;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<String> resultsHistory = new ArrayList<>();
   //연산
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
    //getter
    public ArrayList<String> getResultsHistory() {
        return new ArrayList<>(resultsHistory); // 외부에서 직접 수정하지 못하도록 새로운 리스트 반환
    }

    // setter
    private void setResultsHistory(ArrayList<String> newHistory) {
        this.resultsHistory = new ArrayList<>(newHistory);
    }

    // clear
    public void clearHistory() {
        setResultsHistory(new ArrayList<>()); // 히스토리를 빈 리스트로 설정
        System.out.println("연산 기록이 초기화되었습니다.");
    }

    // fifo
    public void removeFirstHistory() {
        if (!resultsHistory.isEmpty()) {
            ArrayList<String> newHistory = new ArrayList<>(resultsHistory);
            newHistory.remove(0); // 첫 번째 항목 삭제
            setResultsHistory(newHistory);
            System.out.println("가장 먼저 추가된 기록이 삭제되었습니다.");
        } else {
            System.out.println("삭제할 기록이 없습니다.");
        }
    }
}