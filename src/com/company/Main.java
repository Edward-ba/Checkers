package com.company;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
		Player pb = new Player(Player.PlayerType.Black);
		Player pw = new Player(Player.PlayerType.White);
	    Game game = new Game(pb, pw);
	    game.play();
    }
}
	