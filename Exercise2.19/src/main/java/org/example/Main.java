package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();

        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        System.out.print("Введите третье число: ");
        int c = scanner.nextInt();

        int[] values = {a, b, c};
        HashMap<Integer, String> states = new HashMap<>();
        states.put(0, "a");
        states.put(1, "b");
        states.put(2, "c");
        boolean hasValueDivisibleByFive = false;

        for(int i = 0; i < values.length; i++) {
            String delimiter = i == values.length - 1 ? "\n" : ", ";
            if (values[i] % 5 == 0) {
                System.out.print(states.get(i) + "=" + values[i] + delimiter);
                hasValueDivisibleByFive = true;
            }
        }

        if (!hasValueDivisibleByFive) {
            System.out.println("нет значений, кратных 5");
        }

        int divisionResult = a / b;
        System.out.println("Результат целочисленного деления a на b: " + divisionResult);

        double divisionFloating = (double) a / b;
        System.out.println("Результат деления a на b (с плавающей запятой): " + divisionFloating);

        int divisionRoundedUp = (int) Math.ceil(divisionFloating);
        System.out.println("Результат деления a на b, округленного вверх: " + divisionRoundedUp);

        int divisionRoundedDown = (int) Math.floor(divisionFloating);
        System.out.println("Результат деления a на b, округленного вниз: " + divisionRoundedDown);

        int divisionRoundedMath = (int) Math.round(divisionFloating);
        System.out.println("Результат деления a на b, математически округленного: " + divisionRoundedMath);

        int remainder = b % c;
        System.out.println("Остаток от деления b на c: " + remainder);

        int minAB = Math.min(a, b);
        System.out.println("Наименьшее значение из a и b: " + minAB);

        int maxBC = Math.max(b, c);
        System.out.println("Наибольшее значение из b и c: " + maxBC);

        scanner.close();
    }
}