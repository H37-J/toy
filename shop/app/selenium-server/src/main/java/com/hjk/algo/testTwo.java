package com.hjk.algo;

public class testTwo {

    public static void main(String... args) {
        solution(2, 10);
    }

    public static void solution(int start, int end) {
        int count = 0;
        int sum = 1;
        for(int i = start; i <= end; i++) {
            if(primeCheck(i)) {
                count += 1;
                sum *= i;
            }
        }
        System.out.println("count=" + count);
        System.out.println("sum=" + sum);
    }

    public static boolean primeCheck(int number) {
        if(number == 2) {
            return true;
        }

        if(number % 2 == 0) {
            return false;
        }

        for(int i = 2; i <= (int) Math.sqrt(number); i++) {
            if(number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
