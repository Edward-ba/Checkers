package com.company;

public class Pawn extends Token {
    Pawn(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    @Override
    public boolean move(Coordinates beg, Coordinates end)
    {
        int mult = Player.PlayerType.White == type ? 1 : -1;

        // make sure pawn cannot move backwards and col value must change by one
        if (mult*end.r < mult*beg.r || Math.abs(beg.c - end.c) != 1)
            return false;

        board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
        board.grid[beg.r][beg.c] = null;
        return true;
    }

    @Override
    public String print()
    {
        if (this.type == Player.PlayerType.White)
            return "w";
        else
            return "b";
    }
}