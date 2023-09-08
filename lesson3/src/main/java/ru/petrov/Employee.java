package ru.petrov;

import java.util.Random;

public abstract class Employee implements Comparable<Employee> {

    protected String name;
    protected String surName;
    protected int age;
    protected double salary;

    protected static String[] names = new String[]{"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман"};
    protected static String[] surNames = new String[]{"Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов"};
    protected static Random random = new Random();

    private Employee(double salary) {
        this.name = names[random.nextInt(names.length)];
        this.surName = surNames[random.nextInt(names.length)];
        this.age = random.nextInt(18, 60);
        this.salary = salary;
    }

    protected Employee() {
        this(random.nextDouble(20000.0, 50000.0));
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public abstract double getSalary();

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.getSalary(), o.getSalary());
    }
}
