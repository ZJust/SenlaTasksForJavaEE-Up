package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Виктория on 11.09.2017.
 */
public class NODandNOK {
    static {
        System.out.println("Задание №4. Введите два числа: ");
    }

    static String findNODandNOK(String bufferedReader1, String bufferedReader2) throws IOException {
        try {
            int a = Integer.parseInt(bufferedReader1);
            int b = Integer.parseInt(bufferedReader2);
            if (a < 0 || b < 0) throw new NumberFormatException();
            return "Их НОД равен " + nod(a, b) + "\n" + "Их НОК равен " + nok(a, b);
        }
        catch (NumberFormatException e) {
            return "Вы ввели не целые положительные числа.";
        }
    }
    static int nod(int a, int b) {
        int min = Math.min(a, b);
        if (a%min == 0 && b%min == 0)
            return min;
        else
            return nod(a-1, b-1);
    }
    static int nok(int a, int b) {
        int max;
        max = a > b ? a : b;
        for (int count = 2; ; count++) {
            if (max%a == 0 && max%b == 0)
                return max;
            else if (max == a)
                return nok(a*count, b);
            else
                return nok(a, b*count);
        }
    }
}
