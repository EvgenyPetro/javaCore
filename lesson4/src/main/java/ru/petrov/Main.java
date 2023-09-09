package ru.petrov;

import ru.petrov.exceptions.MyArrayDataException;
import ru.petrov.exceptions.MyArraySizeException;

import java.util.Random;

public class Main {
    private static final Random RAND = new Random();

    public static void main(String[] args) {
        String[][] array = createArray();
        printArray(array);

        try {
            sumArrayElement(array);
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMessage());
        } catch (MyArrayDataException ex) {
            System.out.printf(
                    "преобразование не удалось в %d массиве %d ячейки",
                    ex.getArray() + 1,
                    ex.getElement() + 1);
        }

    }

    private static void sumArrayElement(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив массивов не равен 4");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException(i + 1 + " подмассив не равен 4");
            }
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException exception) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        System.out.println(sum);

    }

    public static String[][] createArray() {
        int random = RAND.nextInt(1, 10);
        int firstMassive = 5;
        int size;
        if (random < 8) {
            firstMassive = 4;
        }

        String[][] array = new String[firstMassive][];
        for (int i = 0; i < firstMassive; i++) {
            size = 5;
            if (RAND.nextInt(1, 100) < 97) {
                size = 4;
            }
            array[i] = new String[size];
            for (int j = 0; j < size; j++) {
                if (RAND.nextInt(1, 100) < 98) {
                    array[i][j] = String.valueOf(RAND.nextInt(50));
                } else {
                    array[i][j] = "chars";
                }
            }
        }
        return array;
    }

    public static void printArray(String[][] arr) {
        for (String[] strings : arr) {
            for (String s : strings) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

}