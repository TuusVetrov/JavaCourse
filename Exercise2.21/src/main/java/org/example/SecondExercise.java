package org.example;

import java.util.Scanner;

public class SecondExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String inputString = scanner.nextLine();

        String censoredString = inputString.replaceAll("кака|бяка", "вырезано цензурой");
        System.out.println(censoredString);
        scanner.close();
    }
}
