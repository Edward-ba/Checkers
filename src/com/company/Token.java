package com.company;

abstract public class Token {
    CheckersBoard board;
    Player.PlayerType type;

    public Token(CheckersBoard board, Player.PlayerType type)
    {
        this.board = board;
        this.type = type;
    }

    public abstract boolean move(Coordinates beg, Coordinates end);
    public abstract String print();
    public abstract int score();


    public Player.PlayerType getPlayerType()
    {
        return this.type;
    }
}
