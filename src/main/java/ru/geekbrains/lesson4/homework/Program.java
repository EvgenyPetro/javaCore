package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class Program {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {


            Session session = sessionFactory.openSession();

            HiberCourseRepository repository = new HiberCourseRepository(session);

            Course course1 = new Course("Java", 120);
            Course course2 = new Course("Python", 20);
            Course course3 = new Course("Ruby", 40);


            repository.addCourse(course1);
            repository.addCourse(course2);
            repository.addCourse(course3);

            Arrays.stream(repository.getAll().toArray()).forEach(System.out::println);

            System.out.println(repository.update(2, new Course("Go", 45)));

            System.out.println(repository.getById(1));

            System.out.println(repository.deleteById(3));

            Arrays.stream(repository.getAll().toArray()).forEach(System.out::println);

        }
    }

}
