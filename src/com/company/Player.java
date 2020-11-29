package com.company;
import java.lang.*;
import java.util.*;

public class Player {
    private final PlayerType playerType;
    final Scanner scanner = new Scanner(System.in);

    int[] readInts(String message) {
        System.out.println(message);
        int[] ret = new int[4];

        try {
            String string = scanner.nextLine();
            String[] res = string.split(", ");
            for (int i = 0; i < 4; ++i)
                ret[i] = Integer.parseInt(res[i]);
            return ret;
        } catch (Exception e) {
            return new int[] {-1, -1, -1, -1};
        }
    }

    public enum PlayerType {
        Black,
        White,
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Player(PlayerType pt) {
        this.playerType = pt;
    }

    public void move(Board board, Player player) {
        Coordinates c1 = new Coordinates();
        Coordinates c2 = new Coordinates();

        int[] array = readInts("please enter your inputs as: row of piece, col of piece, row of  place you want to move piece, col of  place you want to move piece");
        c1.r = array[0];
        c1.c = array[1];
        c2.r = array[2];
        c2.c = array[3];

        boolean check = board.move(c1, c2, player);
        if (!check) {
            System.out.println("that doesn't work try again");
            move(board, player);
        }
    }
}
