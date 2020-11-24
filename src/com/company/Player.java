package com.company;
import java.lang.*;
import java.util.*;

public class Player {
    private final PlayerType playerType;
    final Scanner scanner = new Scanner(System.in);

    int readInt(String message) {
        System.out.print(message);
        int ret;

        try {
            String string = scanner.nextLine();
            ret = Integer.parseInt(string);
            return ret;
        } catch (Exception e) {
            return -1;
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

        c1.r = readInt("row of piece: ");
        c1.c = readInt("col of piece: ");
        c2.r = readInt("row of  place you want to move piece: ");
        c2.c = readInt("col of  place you want to move piece: ");

        boolean check = board.move(c1, c2, player);
        if (!check) {
            System.out.println("that doesn't work try again");
            move(board, player);
        }
    }
}
