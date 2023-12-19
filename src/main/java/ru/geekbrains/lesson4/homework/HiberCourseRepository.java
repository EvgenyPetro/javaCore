package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HiberCourseRepository implements CourseRepository {

    Session session;

    public HiberCourseRepository(Session session) {
        this.session = session;
    }

    @Override
    public List<Course> getAll() {

        return session.createQuery("select a from Course a", Course.class).getResultList();
    }

    @Override
    public Course addCourse(Course course) {

        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();

        return course;
    }

    @Override
    public Course getById(int id) {

        return session.get(Course.class, id);

    }

    @Override
    public Course update(int id, Course course) {

        Transaction transaction = session.beginTransaction();
        Course updateCourse = session.get(Course.class, id);
        updateCourse.setTitle(course.getTitle());
        updateCourse.setDuration(course.getDuration());
        session.update(updateCourse);
        transaction.commit();
        return updateCourse;
    }

    @Override
    public boolean deleteById(int id) {

        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, id);
        session.delete(course);
        transaction.commit();
        return true;
    }
}
