package ru.petrov;

public class Worker extends Employee {

    public Worker() {
        super();
    }

    public static Worker getInstance() {
        return new Worker();
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s возраст - %d, рабочий, зарплата - %.0f", name, surName, age, getSalary());

    }


}
