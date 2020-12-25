package com.company;
import java.lang.*;

public class Game {
    CheckersBoard  board = new CheckersBoard();
    Player pb;
    Player pw;

    Game(Player pb, Player pw) {
        this.pb = pb;
        this.pw = pw;
    }

    public void play() {
        Player curPlayer = pw; // player white starts first

        // stating things to the players
        System.out.println("1. This is checkers.");
        System.out.println("2. White goes first.");
        System.out.println("4. The only way to win is by killing all of the other players pieces.");

        // loop until somebody wins
        while (!board.checkWinner()) {
            board.printBoard(); // print the board
            curPlayer.move(board, curPlayer); // player moves their piece
            curPlayer = (curPlayer.getPlayerType() == pb.getPlayerType()) ? pw : pb; // swap between players
        }
    }
}
