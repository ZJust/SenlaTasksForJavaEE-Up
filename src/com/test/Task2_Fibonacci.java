package com.test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Виктория on 11.09.2017.
 */
public class Task2_Fibonacci {

    static {
        System.out.print("Задание №2. Введите число N: ");
    }
    ArrayList<Integer> fib = new ArrayList<>();
    int n, sum, a1 = 0, a2 = 1;
    String bufferReader;

    Task2_Fibonacci(String bufferReader) {
        this.bufferReader = bufferReader;
    }

    void findFibonacciNumbers() throws IOException {
        try {
            n = Math.abs(Integer.parseInt(bufferReader));
            for (int i = 1; i < n; i++) {
                sum = a1 + a2;
                fib.add(sum);
                a1 = a2;
                a2 = sum;
            }
            if (Integer.parseInt(bufferReader) > 0) {
                for (int i = 0; i <= n; i++) {
                    System.out.print(i + "  ");
                }
                System.out.println();
                System.out.print(0 + "  " + 1 + "  ");
                for (int f : fib)
                    System.out.print(f + "  ");
                System.out.println();
            }
            else {
                try {
                    for (int i = 0; i >= Integer.parseInt(bufferReader); i--) {
                        System.out.print(i + "  ");
                    }
                    System.out.println();
                    System.out.print(0 + "  " + 1 + "  ");
                    for (int i = 0; i >= Integer.parseInt(bufferReader); i--) {
                        if (i % 2 == 0)
                            System.out.print(-fib.get(Math.abs(i)) + "  ");
                        else
                            System.out.print(fib.get(Math.abs(i)) + "  ");
                    }
                    System.out.println();
                }
                catch (IndexOutOfBoundsException e) {}
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели какую-то аброкадабру вместо числа.");
        }
    //    System.out.print("0 1 ");

    }
}
