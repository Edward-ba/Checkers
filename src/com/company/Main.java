package com.company;
import java.lang.*;

public class Main {
    public static void main(String[] args) {

    	// pb is player black and pw is player white
		Player pb = new Player(Player.PlayerType.Black);
		Player pw = new Player(Player.PlayerType.White);

		// create a new Game class and play the game
	    Game game = new Game(pb, pw);
	    game.play();


		// Test
		/**
		CheckersBoard board = new CheckersBoard();
		board.move(new Coordinates(5,0), new Coordinates(4,1), pb);
		board.move(new Coordinates(4,1), new Coordinates(3,2), pb);
		board.move(new Coordinates(2,1), new Coordinates(4,3), pw);
		board.printBoard();
		board.move(new Coordinates(5,4), new Coordinates(3,2), pb);
		board.printBoard();
		**/
    }
}
	