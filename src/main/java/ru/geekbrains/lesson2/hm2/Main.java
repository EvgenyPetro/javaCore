package ru.geekbrains.lesson2.hm2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static ArrayList<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {

        Cat cat1 = new Cat("Barsik", 12, "home1");
        Cat cat2 = new Cat("Mursik", 11, "home2");
        Cat cat3 = new Cat("Kisa", 1, "home3");

        Dog dog1 = new Dog("Sharik", 1, "place1");
        Dog dog2 = new Dog("Shrek", 1, "place2");
        Dog dog3 = new Dog("Pit", 1, "place3");

        animals.add(dog1);
        animals.add(dog2);
        animals.add(dog3);

        animals.add(cat1);
        animals.add(cat2);
        animals.add(cat3);

        animals.forEach(Main::printInfo);
        animals.forEach(Main::showObject);

    }


    public static void printInfo(Object obj) {
        Class<?> superClass = obj.getClass().getSuperclass();
        Class<?> aClass = obj.getClass();

        Field[] superClassDeclaredFields = superClass.getDeclaredFields();
        Field[] aClassDeclaredFields = aClass.getDeclaredFields();

        Stream.concat(Arrays.stream(superClassDeclaredFields),
                        Arrays.stream(aClassDeclaredFields))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(System.out::println);
    }

    public static void showObject(Object obj) {
        Class<?> aClass = obj.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Arrays.stream(declaredMethods)
                .filter(method -> method.getName().equals("makeSound"))
                .forEach(method -> {
                    method.setAccessible(true);
                    try {
                        method.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
        ;
    }
}
