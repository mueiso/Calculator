package firstproject;

public class App {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        while (true) {
            int result = performInitialCalculation(calculator);
            if (result == Integer.MIN_VALUE) {
                continue;  // 계산 실패 시 다시 시작
            } else if (result != Integer.MIN_VALUE) {
                System.out.println("계산 결과: " + result);
            }

            // 첫 번째 연산 결과 삭제 여부 처리
            if (calculator.askForClear()) {
                continue;  // 삭제 후 다시 첫 번째 숫자 입력으로 돌아가기
            }

            // 추가 연산 처리
            if (!performAdditionalOperations(calculator)) {
                break;  // 추가 연산이 없으면 종료
            }
        }
    }

    // 첫 번째 연산 수행 메서드
    public static int performInitialCalculation(Calculator calculator) {
        int num1 = calculator.getValidNumber("첫 번째 숫자를 입력하세요: ");
        char operator = calculator.getValidOperator();
        int num2 = calculator.getValidNumber("두 번째 숫자를 입력하세요: ");
        return calculator.performCalculation(num1, num2, operator);
    }

    // 결과 값에 대한 추가 연산 처리
    public static boolean performAdditionalOperations(Calculator calculator) {
        if (calculator.askForAdditionalOperations()) {
            while (true) {
                char additionalOperator = calculator.getValidOperator();
                int additionalNumber = calculator.getValidNumber("숫자를 입력하세요: ");
                int result = calculator.performAdditionalOperation(additionalOperator, additionalNumber);
                if (result != Integer.MIN_VALUE) {
                    System.out.println("추가 연산 결과: " + result);
                }
                if (!calculator.askForAdditionalOperations()) {
                    return false;  // 추가 연산이 없으면 종료
                }
            }
        }
        return false;  // 추가 연산을 하지 않으면 종료
    }
}
