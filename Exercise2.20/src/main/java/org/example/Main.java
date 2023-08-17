package org.example;

import java.util.Random;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[20];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(15) + 1;
        }

        System.out.println("Содержимое массива:");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int value : array) {
            countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        }

        System.out.println("Значения, которые встречаются больше одного раза:");
        for (int key : countMap.keySet()) {
            int count = countMap.get(key);
            if (count > 1) {
                System.out.println("Число '" + key + "' встречается " + count + " раза");
            }
        }
    }
}