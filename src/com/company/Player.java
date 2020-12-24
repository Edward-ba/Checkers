package com.company;
import java.lang.*;
import java.util.*;

public class Player {
    private final PlayerType playerType;
    final Scanner scanner = new Scanner(System.in);

    int[] readInts() {
        System.out.print("Put in your input as curRow, curCol, newRow, newCol. ");
        System.out.println("Eg. is you want to move your piece from 5, 0 to 4, 1 then you would put in (5, 0, 4, 1)");
        int[] ret = new int[4];

        try {
            String string = scanner.nextLine(); // get what the player inputs
            string = string.replaceAll("\\s", ""); // get rid of all the spaces
            String[] res = string.split(","); // put it into a array split by the commas
            for (int i = 0; i < 4; ++i) // loop 4 times to get all  of the strings into ints
                ret[i] = Integer.parseInt(res[i]);
            return ret;
        } catch (Exception e) {
            return new int[] {-1, -1, -1, -1}; // return something that won't work
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

    /**
     *
     * @param board the board itself
     * @param player what player it is
     */
    public void move(Board board, Player player) {
        Coordinates c1 = new Coordinates();
        Coordinates c2 = new Coordinates();

        int[] array = readInts(); // take the array of ints and put them into the coordinates
        c1.r = array[0];
        c1.c = array[1];
        c2.r = array[2];
        c2.c = array[3];

        if (!board.move(c1, c2, player)) { // check if the moving of the piece works if not try again
            System.out.println("that doesn't work try again");
            move(board, player);
        }
    }
}
