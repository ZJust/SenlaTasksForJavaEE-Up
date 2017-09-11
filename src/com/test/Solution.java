package com.test;

/**
 * Created by Виктория on 11.09.2017.
 */

import java.util.*;
import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        //1
        System.out.print("Задание №1. Введите число N: ");
        findPrimeNumbers(bufferedReader.readLine());

        //2
        Task2_Fibonacci fibonacci;
        fibonacci = new Task2_Fibonacci(bufferedReader.readLine());
        fibonacci.findFibonacciNumbers();

        //3
        Point.intersectionOfTwoLineSegments();

        //4
        NODandNOK noDandNOK;
        noDandNOK = new NODandNOK();
        System.out.println(noDandNOK.findNODandNOK(bufferedReader.readLine(), bufferedReader.readLine()));

        //5
        System.out.println("Задание №5. Введите слово для проверки на палиндромность. ");
        isPalindrome(bufferedReader.readLine());

        //6
        System.out.println("Задание №6. Введите строку.");
        System.out.println(delNumbers(bufferedReader.readLine()));

        bufferedReader.close();
    }

    //1
    static void findPrimeNumbers(String bufferedReader) throws IOException {
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        int n;
        try {
            n = Integer.parseInt(bufferedReader);
            if (n < 0) throw new NumberFormatException();
            for (int i = 1; i <= n; i++) {
                boolean isPrime = true;
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime)
                    primeNumbers.add(i);
            }
            if (primeNumbers.size() == 0)
                System.out.println("В данном диапазоне нет простых чисел");
            else {
                System.out.print("Простые числа: ");
                for (int pn : primeNumbers)
                    System.out.print(pn + " ");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Вы ввели какую-то аброкадабру вместо натурального числа.");
        }
        System.out.println();
    }

    //3
    public static class Point {

        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        static void intersectionOfTwoLineSegments() throws IOException {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Введите координаты точки Р1: ");
                Point p1 = new Point(Double.parseDouble(bufferedReader.readLine()), Double.parseDouble(bufferedReader.readLine()));
                System.out.println("Введите координаты точки Р2: ");
                Point p2 = new Point(Double.parseDouble(bufferedReader.readLine()), Double.parseDouble(bufferedReader.readLine()));
                System.out.println("Введите координаты точки Р3: ");
                Point p3 = new Point(Double.parseDouble(bufferedReader.readLine()), Double.parseDouble(bufferedReader.readLine()));
                System.out.println("Введите координаты точки Р4: ");
                Point p4 = new Point(Double.parseDouble(bufferedReader.readLine()), Double.parseDouble(bufferedReader.readLine()));
                if (isIntersection(p1, p2, p3, p4))
                    System.out.println("Отрезки пересекаются");
                else
                    System.out.println("Отрезки не пересекаются");
            }
            catch (NumberFormatException e) {
                System.out.println("Введено не число.");
            }
        }
        private static boolean isIntersection(Point p1, Point p2, Point p3, Point p4) {

//если нужно, переставляем точки, чтобы было p1.x <= p2.x и p3.x <= p4.x
            if (p2.x < p1.x) {
                Point tmp = p1;
                p1 = p2;
                p2 = tmp;
            }
            if (p4.x < p3.x) {
                Point tmp = p3;
                p3 = p4;
                p4 = tmp;
            }


            if (p2.x < p3.x) {
                return false; //нет взаимной абсциссы
            }

//если оба отрезка вертикальные и лежат на одном X
            if((p1.x - p2.x == 0) && (p3.x - p4.x == 0)) {
                if(p1.x == p3.x) {
                    if (!((Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y)) || (Math.min(p1.y, p2.y) > Math.max(p3.y, p4.y)))) {
                        return true;
                    }
                }
                return false;
            }

//если первый отрезок вертикальный, найдём Xa, Ya - точки пересечения двух прямых
            if (p1.x - p2.x == 0) {
                double Xa = p1.x;
                double A2 = (p3.y - p4.y) / (p3.x - p4.x);
                double b2 = p3.y - A2 * p3.x;
                double Ya = A2 * Xa + b2;
                if (p3.x <= Xa && p4.x >= Xa && Math.min(p1.y, p2.y) <= Ya && Math.max(p1.y, p2.y) >= Ya) {
                    return true;
                }
                return false;
            }

//если второй отрезок вертикальный, найдём Xa, Ya - точки пересечения двух прямых
            if (p3.x - p4.x == 0) {
                double Xa = p3.x;
                double A1 = (p1.y - p2.y) / (p1.x - p2.x);
                double b1 = p1.y - A1 * p1.x;
                double Ya = A1 * Xa + b1;
                if (p1.x <= Xa && p2.x >= Xa && Math.min(p3.y, p4.y) <= Ya && Math.max(p3.y, p4.y) >= Ya) {
                    return true;
                }
                return false;
            }

//оба отрезка невертикальные
            double A1 = (p1.y - p2.y) / (p1.x - p2.x);
            double A2 = (p3.y - p4.y) / (p3.x - p4.x);
            double b1 = p1.y - A1 * p1.x;
            double b2 = p3.y - A2 * p3.x;
            if (A1 == A2) {
                return false; //отрезки параллельны
            }

//Xa - абсцисса точки пересечения двух прямых
            double Xa = (b2 - b1) / (A1 - A2);
            if ((Xa < Math.max(p1.x, p3.x)) || (Xa > Math.min( p2.x, p4.x))) {
                return false; //точка Xa находится вне пересечения проекций отрезков на ось X
            }
            else {
                return true;
            }

        }
    }

    //5
    static void isPalindrome (String bufferedReader) throws IOException {
        String rev_str = new StringBuilder(bufferedReader).reverse().toString();
        if (bufferedReader.toLowerCase().equals(rev_str.toLowerCase()))
            System.out.println("Слово \"" + bufferedReader + "\" является палиндромом.");
        else System.out.println("Слово \"" + bufferedReader + "\" не является палиндромом.");

    }

    //6
    static String delNumbers(String bufferedReader) throws IOException {
        return bufferedReader.replaceAll("[0-9]", "");
    }

}


