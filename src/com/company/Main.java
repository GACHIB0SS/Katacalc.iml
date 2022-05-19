package com.company;
import java.util.*;
import java.lang.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        System.out.println(calc(sc.nextLine()));
    }

    public static String calc(String input) throws Exception {
        String value = input.replaceAll(" ", "_");
        String[] arr = value.split("[_]");
        if (arr.length > 3) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }
        if (arr.length < 3) {
            throw new Exception("Cтрока не является математической операцией");
        }
        List<String> roman = new ArrayList<>(Arrays.asList(new Roman2Arabic().getRoman()));

        if (!roman.contains(arr[0]) && !roman.contains(arr[2])) {
            int num1 = Integer.parseInt(arr[0]);
            int num2 = Integer.parseInt(arr[2]);
            if (0 >= num1 || 0 >= num2 ) {
                throw new Exception("Числа меньше или равны 0");
            }
            if (10 < num1 || 10 < num2 ) {
                throw new Exception("Числа больше 10");
            }

            char op = arr[1].charAt(0);
            return String.valueOf(calculated(num1, num2, op));
        }
        if (roman.contains(arr[0]) && roman.contains(arr[2])) {
            int num1 = 0;
            num1 = numberHashMap(arr[0]);
            int num2 = 0;
            num2 = numberHashMap(arr[2]);

            char op = arr[1].charAt(0);
            int result = calculated(num1, num2, op);
            if (result < 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            return new Roman2Arabic().convertNumToRoman(result);
        }
        throw new Exception ("Используются одновременно разные системы счисления");
    }
    public static int calculated(int num1, int num2, char op) throws IllegalArgumentException {
        int result = 0;

        switch (op) {
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
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }

    static int numberHashMap(String romanNumber) throws Exception {
        Map<String, Integer> mapRoman = new HashMap<>();
        mapRoman.put("I", 1);
        mapRoman.put("II", 2);
        mapRoman.put("III", 3);
        mapRoman.put("IV", 4);
        mapRoman.put("V", 5);
        mapRoman.put("VI", 6);
        mapRoman.put("VII", 7);
        mapRoman.put("VIII", 8);
        mapRoman.put("IX", 9);
        mapRoman.put("X", 10);
        if (!mapRoman.containsKey(romanNumber)) {
            throw new Exception("Передано некорректное римское число");
        }
        return mapRoman.get(romanNumber);
    }
}
