package ru.petrov.exceptions;

public class MyArrayDataException extends MyArrayException{
    private int array;
    private int element;

    public MyArrayDataException(int array, int element) {
        this.array = array;
        this.element = element;
    }

    public int getArray() {
        return array;
    }

    public void setArray(int array) {
        this.array = array;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
}
