package org.example;

public class Calculator {
    Operation operation;
    public Calculator(Operation operation) {
        this.operation = operation;
    }
    public void calc(double a, double b) {
        try {
            System.out.println(operation.getResult(a, b));
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }
}
