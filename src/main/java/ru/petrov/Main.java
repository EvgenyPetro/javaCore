package ru.petrov;

import java.util.*;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {

        Map<Integer, Integer> switchPosition = new HashMap<>();
        Map<Integer, Integer> nonSwitchPosition = new HashMap<>();

        for (int i = 0; i < 1000; i++) {

            var position = random.nextInt(1, 3);

            var board = createBoard();
            var firstChoice = firstChoice(board, position);

            nonSwitchPosition.put(i, nonSwitchPosition(firstChoice, position));
            switchPosition.put(i, switchPosition(firstChoice, position));

        }

        System.out.printf("%s - %d побед\n","При смене первоначального выбора",switchPosition.values().stream().mapToInt(Integer::intValue).sum());
        System.out.printf("%s - %d побед\n","Не меняя выбора",nonSwitchPosition.values().stream().mapToInt(Integer::intValue).sum());
    }

    public static Map<String, Boolean> createBoard() {
        Map<String, Boolean> board = new HashMap<>(3);
        List<Boolean> list = new ArrayList<>();

        list.add(false);
        list.add(true);
        list.add(false);
        Collections.shuffle(list);

        for (int i = 0; i < 3; i++) {
            board.put(String.valueOf(i + 1), list.get(i));
        }

        return board;
    }

    public static Map<String, Boolean> firstChoice(Map<String, Boolean> board, int index) {

        if (index != 1 && !board.get(String.valueOf(1))) {
            board.remove(String.valueOf(1));
        } else if (index != 2 && !board.get(String.valueOf(2))) {
            board.remove(String.valueOf(2));
        } else {
            board.remove(String.valueOf(3));
        }
        return board;
    }

    public static int nonSwitchPosition(Map<String, Boolean> board, int position) {

        if (board.get(String.valueOf(position))) {
            return 1;
        } else return 0;

    }

    public static int switchPosition(Map<String, Boolean> board, int position) {

        board.remove(String.valueOf(position));
        if (board.values().stream().findFirst().get()) {
            return 1;
        } else return 0;

    }


}
