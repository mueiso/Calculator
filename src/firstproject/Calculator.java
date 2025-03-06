package firstproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    public Scanner scanner = new Scanner(System.in);
    private List<Integer> results;

    public Calculator() {
        results = new ArrayList<>();
    }

    // 유효한 숫자 입력받기 메서드
    public int getValidNumber(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (num >= 0) return num;
                else System.out.println("0 이상의 수를 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
            }
        }
    }

    // 유효한 연산자 입력받기 메서드
    public char getValidOperator() {
        while (true) {
            System.out.println("사칙연산(+, -, *, /)을 입력하세요: ");
            String input = scanner.nextLine();
            if (input.length() == 1 && "+-*/".contains(input)) return input.charAt(0);
            else System.out.println("잘못된 입력입니다. 사칙연산 기호를 입력해주세요.");
        }
    }

    // 사칙연산 수행 메서드
    public int performCalculation(int num1, int num2, char operator) {
        int result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    return Integer.MIN_VALUE;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("잘못된 연산자입니다.");
                return Integer.MIN_VALUE;
        }
        results.add(result);
        return result;
    }

    // 연산 결과 삭제 여부 묻기 메서드
    public boolean askForClear() {
        while (true) {
            System.out.print("가장 먼저 계산된 결과값을 지우시겠습니까? (clear / no): ");
            String input = scanner.nextLine();
            if ("clear".equalsIgnoreCase(input)) {
                deleteFirstResult();
                return true;
            } else if ("no".equalsIgnoreCase(input)) {
                return false;
            } else {
                System.out.println("잘못된 입력입니다. 'clear' 또는 'no' 를 입력하세요.");
            }
        }
    }

    // 첫 번째 저장된 연산 결과 삭제 메서드
    private void deleteFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0);
            System.out.println("가장 먼저 연산된 결과가 삭제되었습니다.");
        } else {
            System.out.println("삭제할 연산 결과가 없습니다.");
        }
    }

    // 추가 연산 여부 묻기 메서드
    public boolean askForAdditionalOperations() {
        while (true) {
            System.out.print("마지막으로 계산된 값에 추가 연산을 하시겠습니까? (yes / exit): ");
            String input = scanner.nextLine();
            if (results.isEmpty()) {
                System.out.println("연산 결과가 없습니다. 먼저 계산을 수행해주세요.");
                return false;
            }
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                System.exit(0);  // 프로그램 종료
            } else if (input.equalsIgnoreCase("yes")) {
                return true;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 마지막 결과값에 추가 연산 수행 메서드
    public int performAdditionalOperation(char operator, int number) {
        int lastResult = results.get(results.size() - 1);
        int result;

        switch (operator) {
            case '+':
                result = lastResult + number;
                break;
            case '-':
                result = lastResult - number;
                break;
            case '*':
                result = lastResult * number;
                break;
            case '/':
                if (number == 0) {
                    System.out.println("0으로 나눌 수 없습니다.");
                    return Integer.MIN_VALUE;
                }
                result = lastResult / number;
                break;
            default:
                System.out.println("잘못된 연산자입니다.");
                return Integer.MIN_VALUE;
        }

        results.add(result);
        return result;
    }
}
