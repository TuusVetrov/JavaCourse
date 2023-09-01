package org.example;

public class Multiplier implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a * b;
    }
}
