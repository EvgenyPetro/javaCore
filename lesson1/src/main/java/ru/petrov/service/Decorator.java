package ru.petrov.service;

public class Decorator {

    public static String decorate(int number){
        return String.format("возвращаемое число %d!", number);
    }
}
