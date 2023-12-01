package ru.geekbrains.lesson2.hm2;

public class Dog extends Animal {

    public Dog(String name, int age, String place) {
        super(name, age);
        this.place = place;
    }

    private String place;

    public void running() {
        System.out.println("Cat Running");
    }

//    public void makeSound() {
//        System.out.println("Gav");
//    }
}
