package com.hjk.algo;

import java.util.HashMap;
import java.util.Map;

public class testOne {

    public static void main(String... args) {
        solution(10, 15);
    }

    public static void solution(int start, int end) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        StringBuilder message = new StringBuilder("total = ");
        for(int i = start; i <= end; i++) {
            String[] arr = String.valueOf(i).split("");

            for(String str : arr) {
                Integer count = map.getOrDefault(Integer.valueOf(str), 0) + 1;
                map.put(Integer.valueOf(str), count);

                total += Integer.parseInt(str);
                message.append(str).append(" + ");
            }
        }

        message = new StringBuilder(message.toString().substring(0, message.toString().length() - 2));

        map.forEach((key, value) -> {
            System.out.print(key + " = ");
            System.out.println(value);
        });
        System.out.println(message + " = " + total);
    }


}
