package ru.petrov;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char USER_PIC = 'X';
    private static final char AI_PIC = 'O';
    private static final char EMPTY_PIC = '✺';

    private static final int ROWS = 2;
    private static final int COLUMN = 4;
    private static final int winnerCombination = 2;

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();

    private static char[][] field;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
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
        field = new char[ROWS][COLUMN];
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMN; y++) {
                field[x][y] = EMPTY_PIC;
            }
        }
    }

    private static void printField(char[][] field) {

        System.out.print("+-");
        for (int x = 1; x < COLUMN + 1; x++) {
            System.out.print(x + "-");
        }
        System.out.println();

        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMN; y++) {
                if (y == 0) {
                    System.out.print(x + 1 + "⎜");
                } else System.out.print("⎜");
                System.out.print(field[x][y]);
            }
            System.out.println();
        }

        for (int x = 0; x < ROWS * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;

        do {

            while (true) {
                System.out.printf("Введите координаты хода X (от 1 до %d)\n", ROWS);
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
                System.out.printf("Введите координаты хода Y(от 1 до %d)\n", COLUMN);
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


    private static void AITurn() {
        int x;
        int y;

        x = rand.nextInt(ROWS);
        y = rand.nextInt(COLUMN);
        while (true) {
            if (field[x][y] == EMPTY_PIC) {
                field[x][y] = AI_PIC;
                return;
            } else {
                x = rand.nextInt(ROWS);
                y = rand.nextInt(COLUMN);
            }
        }
    }

    private static boolean isCellValid(int x, int y) {
        return (x >= 0 && x < ROWS && y >= 0 && y < COLUMN);
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == EMPTY_PIC;
    }

    private static boolean checkWinnerCombination(char c, String s) {
        if (checkXY(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDiagonal(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean checkXY(char symbol) {

        for (int i = 0; i < ROWS; i++) {
            int cols = 0;
            for (int j = 0; j < COLUMN; j++) {
                cols = (field[i][j] == symbol) ? cols + 1 : 0;
                if (cols == winnerCombination) {
                    return true;
                }
                int row = 0;
                for (int x = 0; x < ROWS; x++) {
                    row = (field[x][j] == symbol) ? row + 1 : 0;
                    if (row == winnerCombination) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal(char symbol) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMN; j++) {
                int up = 0;
                int down = 0;
                for (int x = 0; x < winnerCombination; x++) {
                    if (isCellValid(i + x, j + x)) {
                        up = field[i + x][j + x] == symbol ? up + 1 : 0;
                    }
                    if (isCellValid(i + x, j - x)) {
                        down = field[i + x][j - x] == symbol ? down + 1 : 0;
                    }
                    if (up == winnerCombination || down == winnerCombination) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMN; y++) {
                if (isCellEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

}
