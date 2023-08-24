package org.example;

import java.time.LocalTime;
import java.util.stream.IntStream;

public class Student implements Learner {
    @Override
    public void learn() {
        printLocalTime();
        System.out.println("Я учусь. .zZ");
        IntStream.range(0, 1000000).forEach(i -> Math.log(i));
        System.out.println("Я закончил учиться");
    }

    public  void printLocalTime() {
        LocalTime currentTime = LocalTime.now();
        System.out.println("Текущее время: " + currentTime);
    }
}
