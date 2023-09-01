package org.example;

public class Main {
    public static void main(String[] args) {
        double a = 2.0;
        double b = 5.0;

        Calculator calculator = new Calculator(new Adder());
        calculator.calc(a, b);

        calculator = new Calculator(new Subtractor());
        calculator.calc(a, b);

        calculator = new Calculator(new Multiplier());
        calculator.calc(a, b);

        calculator = new Calculator(new Divider());
        calculator.calc(a, b);
    }
}