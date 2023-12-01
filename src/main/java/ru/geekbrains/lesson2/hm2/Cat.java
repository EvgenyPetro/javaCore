package ru.geekbrains.lesson2.hm2;

public class Cat extends Animal {

    public Cat(String name, int age, String home) {
        super(name, age);
        this.home = home;
    }

    private String home;

    public void running() {
        System.out.println("Cat Running");
    }

    public void makeSound() {
        System.out.println("Moy " + home);
    }
}
