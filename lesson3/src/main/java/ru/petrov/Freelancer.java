package ru.petrov;

public class Freelancer extends Employee {

    public Freelancer() {
        super();
    }

    public static Freelancer getInstance() {
        return new Freelancer();
    }

    @Override
    public double getSalary() {
        return 20.8 * 8 * super.salary/150;
    }

    @Override
    public String toString() {
        return String.format("%s %s возраст - %d, фрилансер, зарплата - %.0f", name, surName, age, getSalary());
    }

}
