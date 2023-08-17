package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FourthExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку даты (формат 31.12.2020): ");
        String inputDate = scanner.nextLine();

        String formattedDate = formatDateUsingDateFormat(inputDate);
        System.out.println("Преобразованная дата (с использованием SimpleDateFormat): " + formattedDate);
    }

    public static String formatDateUsingDateFormat(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "Неверный формат даты!";
        }
    }
}
