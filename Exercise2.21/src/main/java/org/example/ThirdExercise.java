package org.example;

import java.util.Scanner;

public class ThirdExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку даты (формат 31.12.2020): ");
        String inputDate = scanner.nextLine();

        String formattedDate = formatDate(inputDate);
        System.out.println("Преобразованная дата: " + formattedDate);
    }

    public static String formatDate(String inputDate) {
        String[] parts = inputDate.split("\\.");
        if (parts.length == 3) {
            return parts[2] + "-" + parts[1] + "-" + parts[0];
        } else {
            return "Неверный формат даты!";
        }
    }
}
