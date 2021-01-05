package com.company;

public class King extends Token {
    King(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    @Override
    public boolean move(Coordinates beg, Coordinates end) {
        Player.PlayerType thisPlayerType = board.grid[beg.r][beg.c].getPlayerType();
        Player.PlayerType otherPlayerType = board.grid[beg.r][beg.c].getPlayerType();
        // row and col value must change by one
        if (Math.abs(beg.c - end.c) == 1 || Math.abs(beg.r - beg.c) == 1) {
            board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
            board.grid[beg.r][beg.c] = null;
            return true;
        }
        else if (Math.abs(beg.c - end.c) == 2 || Math.abs(beg.r - beg.c) == 2) {
            if (beg.c < end.c &&
                    beg.r < end.r &&
                    board.grid[beg.r - 1][beg.c - 1].getPlayerType() == otherPlayerType) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r - 1][beg.c - 1] = null;
                return true;
            }
            else if (beg.c < end.c &&
                    beg.r > end.r &&
                    board.grid[beg.r + 1][beg.c - 1].getPlayerType() == otherPlayerType) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r - 1][beg.c - 1] = null;
                return true;
            }
            else if (beg.c > end.c &&
                    beg.r < end.r &&
                    board.grid[beg.r - 1][beg.c + 1].getPlayerType() == otherPlayerType) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r - 1][beg.c - 1] = null;
                return true;
            }
            else if (beg.c > end.c &&
                    beg.r > end.r &&
                    board.grid[beg.r + 1][beg.c + 1].getPlayerType() == otherPlayerType) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r - 1][beg.c - 1] = null;
                return true;
            }
        }
        return false;   
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