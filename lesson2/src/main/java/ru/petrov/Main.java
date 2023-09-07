package ru.petrov;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char USER_PIC = 'X';
    private static final char AI_PIC = 'O';
    private static final char EMPTY_PIC = '✺';

    private static final int fieldX = 4;
    private static final int fieldY = 4;
    private static final int winnerCombination = 2;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();

    private static char[][] field;

    public static void main(String[] args) {
        init();
        printField(field);
        while (true) {
            humanTurn();
            printField(field);
            if (checkWinnerCombination(USER_PIC, "User WINNER")) {
                break;
            }
            AITurn();
            printField(field);
            if (checkWinnerCombination(AI_PIC, "AI WINNER")) {
                break;
            }
        }
    }

    private static void init() {
        field = new char[fieldX][fieldY];

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
        int x, y;

        do {

            while (true) {
                System.out.printf("Введите координаты хода X (от 1 до %d)\n", fieldX);
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }

            while (true) {
                System.out.printf("Введите координаты хода Y(от 1 до %d)\n", fieldY);
                if (scanner.hasNextInt()) {
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = USER_PIC;

    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == EMPTY_PIC;
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

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= fieldX || y < 0 || y >= fieldY) {
            return false;
        }
        return field[x][y] == EMPTY_PIC;
    }

    private static boolean checkWinnerCombination(char c, String s) {
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean checkDraw() {
        for (int x = 0; x < fieldX; x++) {
            for (int y = 0; y < fieldY; y++) {
                if (isCellEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }



    private static boolean checkWin(char c){

        // Проверка по трем горизонталям
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        // Проверка по диагоналям
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }
}
