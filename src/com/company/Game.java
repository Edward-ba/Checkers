package com.company;
import java.lang.*;

public class Game {
    Board  board = new Board();
    Player pb;
    Player pw;

    Game(Player pb, Player pw) {
        this.pb = pb;
        this.pw = pw;
    }

    public void play()
    {
        Player curPlayer = pb;
        System.out.println("1. This is checkers.");
        System.out.println("2. Black goes first.");
        System.out.println("4. The only way to win is by killing all of the other persons pieces.");

        while (!board.checkWinner()) {
            board.printBoard();
            curPlayer.move(board, curPlayer);
            curPlayer = (curPlayer.getPlayerType() == pb.getPlayerType()) ? pw : pb;
        }
    }
}
