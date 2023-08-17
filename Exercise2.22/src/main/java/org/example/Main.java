package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final Calendar calendar = Calendar.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите дату (формат 31.12.2020): ");
        String inputDate = scanner.nextLine();

        Date date = null;

        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты!");
            return;
        }

        Date increasedDate = setOffsetToDate(date, 45);
        System.out.println("Дата после добавления 45 дней: " + dateFormat.format(increasedDate));

        Date startOfYear = shiftToStartOfYear(date);
        System.out.println("Дата после сдвига на начало года: " + dateFormat.format(startOfYear));

        Date increasedWorkDaysDate = setOffsetOfWorkDays(date, 10);
        System.out.println("Дата после добавления 10 рабочих дней: " + dateFormat.format(increasedWorkDaysDate));
        
        System.out.print("Введите вторую дату (формат 31.12.2020): ");
        String secondInputDate = scanner.nextLine();

        Date secondDate = null;

        try {
            secondDate = dateFormat.parse(secondInputDate);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты!");
            return;
        }

        int workDaysBetweenDates = calculateWorkDaysBetweenDates(date, secondDate);
        System.out.println("Количество рабочих дней между датами: " + workDaysBetweenDates);
    }

    private static Date setOffsetToDate(Date startDate, int offset) {
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, offset);

        return calendar.getTime();
    }

    private static Date setOffsetOfWorkDays(Date startDate, int offsetWorkDays) {
        calendar.setTime(startDate);
        int workDays = 0;

        while (workDays < offsetWorkDays) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workDays++;
            }
        }

        return calendar.getTime();
    }

    private static Date shiftToStartOfYear(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static int calculateWorkDaysBetweenDates(Date startDate, Date endDate) {
        calendar.setTime(startDate);
        int workDays = 0;

        while (calendar.getTime().before(endDate) || calendar.getTime().equals(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workDays++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return workDays;
    }
}
