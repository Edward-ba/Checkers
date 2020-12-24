package com.company;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
    /*
    	// pb is player black and pw is player white
		Player pb = new Player(Player.PlayerType.Black);
		Player pw = new Player(Player.PlayerType.White);

		// create a new Game class and play the game
	    Game game = new Game(pb, pw);
	    game.play();
	*/

		CheckersBoard cb = new CheckersBoard();
		cb.printBoard();
    }
}
	