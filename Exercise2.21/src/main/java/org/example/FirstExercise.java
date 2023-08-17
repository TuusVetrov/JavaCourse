package org.example;

import java.util.Scanner;

public class FirstExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String fullString = scanner.nextLine();
        System.out.print("Введите подстроку: ");
        String substring = scanner.nextLine();

        int count = countSubstringOccurrences(fullString, substring);
        System.out.println("Подстрока встречается " + count + " раз(а) в строке.");

        scanner.close();
    }

    public static int countSubstringOccurrences(String fullString, String substring) {
        int count = 0;
        int index = fullString.indexOf(substring);

        while (index != -1) {
            count++;
            index = fullString.indexOf(substring, index + 1);
        }

        return count;
    }
}