package ru.petrov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (rand.nextInt() % 2 == 0) {
                employees.add(Worker.getInstance());
            } else employees.add(Freelancer.getInstance());
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Collections.sort(employees);
        System.out.println("**********************");

        employees.forEach(System.out::println);

        System.out.println("**********************");

        employees.sort(new NameSurNameComparator());

        employees.forEach(System.out::println);


    }

}
