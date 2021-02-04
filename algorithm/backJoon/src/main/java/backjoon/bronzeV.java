package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class bronzeV {

//    https://solved.ac/problems/level


    public static void main(String[] args) throws Exception{
//    1000 A+B 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
//    1001 A-B 두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.
        Scanner sc = new Scanner(System.in);
        System.out.println("A+B,A-B");
        int a, b;
        a = sc.nextInt();
        b = sc.nextInt();

        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println("");

//    1271 엄청난 부자2
        System.out.println("1271 엄청난 부자2");
        int m;
        int n;
        m = sc.nextInt();
        n = sc.nextInt();

        System.out.println(m / n);
        System.out.println(m % n);

//   1550 16진수
        System.out.println("1550 16진수");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(Integer.parseInt(input, 16));

//   2475 검증수
        System.out.println("2475 검증수");
        int result = 0;
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            cnt += Math.pow(sc.nextInt(), 2);
        }
        System.out.println(cnt % 10);

//        2845 파티가 끝나고 난 뒤
        System.out.println("2845 파티가 끝나고 난 뒤");
        int L; //사람의수
        int P; //파티가 열렸던 곳의 넓이
        L = sc.nextInt();
        P = sc.nextInt();
        // l x p - 신문사람수

        int[] arr1 = new int[5];
        int[] arr2 = new int[5];

        for (int i = 0; i < 5; i++) {
            arr1[i] = sc.nextInt();
            arr2[i] = arr1[i] - (L * P);
            System.out.print(arr2[i] + " ");
        }
    }

}
