package backjoon;

import java.util.Scanner;

public class bronzeV {

//    https://solved.ac/problems/level


    public static void main(String[] args) {
//    1000 A+B 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
//    1001 A-B 두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.
        Scanner sc = new Scanner(System.in);
        int a, b;
        a = sc.nextInt();
        b = sc.nextInt();

        System.out.println(a + b);
        System.out.println(a - b);

//    1271 엄청난 부자2
        int m;
        int n;
        m = sc.nextInt();
        n = sc.nextInt();

        System.out.println(m / n);
        System.out.println(m % n);

//   1550 16진수


    }
}
