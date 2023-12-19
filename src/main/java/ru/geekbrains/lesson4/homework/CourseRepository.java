package ru.geekbrains.lesson4.homework;

import java.util.List;

public interface CourseRepository {

    List<Course> getAll();

    Course addCourse(Course course);

    Course getById(int id);

    Course update(int id, Course course);


    boolean deleteById(int id);

}
