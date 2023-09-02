package ru.petrov;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char USER_PIC = 'X';
    private static final char AI_PIC = 'O';
    private static final char EMPTY_PIC = '✺';

    private static final int fieldX = 4;
    private static final int fieldY = 4;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();

    private static final char[][] field = new char[fieldX][fieldY];

    public static void main(String[] args) {
        init();
        printField(field);
        while (true) {
            humanTurn();
            printField(field);
            if (checkWin(USER_PIC)){
                return;
            }
            AITurn();
            printField(field);
            if (checkWin(USER_PIC)){
                return;
            }
        }
    }

    private static void init() {

        for (int x = 0; x < fieldX; x++) {
            for (int y = 0; y < fieldY; y++) {
                field[x][y] = EMPTY_PIC;
            }
        }
    }

    private static void printField(char[][] field) {

        System.out.print("+-");
        for (int x = 1; x < fieldX + 1; x++) {
            System.out.print(x + "-");
        }
        System.out.println();

        for (int x = 0; x < fieldX; x++) {
            for (int y = 0; y < fieldY; y++) {
                if (y == 0) {
                    System.out.print(x + 1 + "⎜");
                } else System.out.print("⎜");
                System.out.print(field[x][y]);
            }
            System.out.println();
        }

        for (int x = 0; x < fieldX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        System.out.printf("Введите координаты хода X и Y(от 1 до %d)\n", fieldX);
        System.out.println(">>>");
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        field[x][y] = USER_PIC;
    }

    private static void AITurn() {
        int x;
        int y;

        x = rand.nextInt(fieldX);
        y = rand.nextInt(fieldY);
        while (true) {
            if (field[x][y] == EMPTY_PIC) {
                field[x][y] = AI_PIC;
                return;
            } else {
                x = rand.nextInt(fieldX);
                y = rand.nextInt(fieldY);
            }
        }

    }

    private static boolean checkWin(char c) {
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) {
            System.out.println("Winner " + c);
            return true;
        }
        return false;
    }
}
