# 계산기 프로젝트

### ⭐ 내가 구현하고자 하는 계산기 프로젝트의 코드 동작 흐름 :

1. 첫 번째 숫자 입력 받기

2. 연산자 입력받기

3. 두 번째 숫자 입력받기

4. 가장 마지막 결과값을 지우기 원하는지 입력받기

5. 안 지우면 6번으로 넘어가고 / 지우기 하면 마지막 결과값 지우고 다시 1번으로 돌아가기

6. 마지막 결과값에 대한 추가 연산 원하는지 입력받기

7. 원하면 추가 연산 하기 / 원하지 않으면 exit 입력으로 프로그램 종료

![image](https://github.com/user-attachments/assets/3c65c00f-961b-4003-97f5-1b5cc1c056dc)

-----

**요구사항 체크리스트 ⬜✅ 에 따른 코딩 과정 :**

### * 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 사진 Calculator 클래스를 생성
  
  ✅ 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
  
  ✅ 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
  
  ✅ 1) 양의 정수 2개(0 포함)와 연산 기호를 매개변수로 받아 사칙연산(➕,➖,✖️,➗) 기늘을 수행한 후,
 
  2\) 결과 값을 반환하는 메서드와 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성

```java
package firstproject;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    // 연산 결과를 저장하는 컬렉션 필드
    public List<Integer> results;

    // 생성자
    public Calculator() {
        results = new ArrayList<>();  // 연산 결과를 저장할 ArrayList 초기화
    }

    // 사칙연산 수행
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
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("0으로 나눌 수 없습니다.");
                    return Integer.MIN_VALUE; // 에러 표시
                }
                break;
            default:
                System.out.println("잘못된 연산자입니다.");
                return Integer.MIN_VALUE; // 에러 표시
        }

        // 결과를 리스트에 저장
        results.add(result);
        return result;
    }
}

```

-----

### * Lv1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정

  ✅ 연산 수행 역할은 Calculator 클래스가 담당
  
  ✅ 연산 결과는 Calculator 클래스의 연산 결과를 저장하는 필드에 저장
  
  ✅ 소스 코드 수정 후에도 수정 전의 기능들이 반드시 똑같이 동작해야 한다

```java
package firstproject;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {   // "exit"를 입력하기 전까지 계속 반복

            // 첫 번째 숫자 입력받기
            int num1 = getValidNumber(scanner, "첫 번째 숫자를 입력하세요: ");

            // 사칙연산 입력받기
            char operator = getValidOperator(scanner);

            // 두 번째 숫자 입력받기
            int num2 = getValidNumber(scanner, "두 번째 숫자를 입력하세요: ");

            // 사칙연산 수행 후 결과 출력
            int result = calculator.performCalculation(num1, num2, operator);
            // Calculator 클래스의 performCalculation 호출

            if (result != Integer.MIN_VALUE) {
                System.out.println("계산 결과: " + result);
            }

            // 계속 계산할지 물어보기
            if (!getContinueInput(scanner)) {
                System.out.println("계산기를 종료합니다.");
                return;
            }
        }
    }
```

-----

### * App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정(캡슐화)

  ✅ 간접 접근을 통해 필드에 접근하여 가져올 수 있도록 구현 (Getter 메서드)
  
  ✅ 간접 접근을 통해 필드에 접근하여 수정할 수 있도록 구현 (Setter 메서드)
  
  ✅ 위 요구사항을 모두 구현 했다면 App 클래스의 main 메서드에서 위에서 구현한 메서드를 활용해보자

```java
// 연산 결과를 저장하는 컬렉션 필드
    private List<Integer> results;
```

private 으로 설정하여 외부에서 직접 접근이 불가하도록 해주고,

```java
// 연산 결과 리스트를 반환하는 getter 메서드
    public List<Integer> getResults() {
        return new ArrayList<>(results);
```

results의 복사본을 반환하여 외부에서 수정 못 하도록 했다

-----

### * Calcualtor 클래스에서 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후 App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정

  ✅ 키워드 : `컬렉션`
  
  ✅ 컬렉션에서 '값을 넣고 제거하는 방법을 이해하는 것'이 중요!

연산 결과를 `results` 에 추가할 때

```java
results.add(result);  // results 에 연산 때마다의 result 를 저장
```

연산 결과를 삭제할 때

```java
results.remove();  // 배열 형태의 리스트라면 괄호 안에 인덱스 넘버를 넣어 몇 번째를 삭제할지 정할 수 있다
```

-----

### ⭐ 느낀점

**코드의 가독성과 재사용성을 높이기 위해 최대한 App 클래스의 main 메서드에서는 Calculator 클래스의 메서드를 활용하는 형태로 코딩하려고 노력할 필요성을 느낀다.**

⚠️ 또한, 메서드 마다 확실하게 한 가지의 기능을 하도록 코드를 짜는 것이 객체지향성에 걸맞다는 점을 잊지 말자.

**가장 애를 먹었던 부분이 Calculator 클래스에서 만든 메서드를 App 클래스로 불러오는 과정에서 가장 힘들었던 것 같다.**

아직 문법이 익숙하지 않아서 그런지 내가 원하는 코드의 동작 흐름을 구현하기 위해 코딩할 때마다 잦은 수정이 필요했고 그에 따라 시간이 많이 소요됐다. 하지만, 그 과정에서 문법도 조금 더 익숙해진 것 같고, 코드를 전체적으로 보면서 효율적으로 짜야 한다는 생각도 하게 되었다.
