package ru.petrov.service;

public class Context {

    public void run() {
        System.out.println(Decorator.decorate(Calculator.sum(2, 10)));
        System.out.println(Decorator.decorate(Calculator.difference(10, 4)));
        System.out.println(Decorator.decorate(Calculator.divine(20, 5)));
        System.out.println(Decorator.decorate(Calculator.multiplication(5, 5)));
    }
}
