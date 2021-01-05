package com.company;

public class Pawn extends Token {
    Pawn(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    @Override
    public boolean move(Coordinates beg, Coordinates end) {
        int mult = Player.PlayerType.Black == type ? 1 : -1;
        Player.PlayerType thisPlayerType = board.grid[beg.r][beg.c].getPlayerType();
        Player.PlayerType otherPlayerType = thisPlayerType == Player.PlayerType.Black ? Player.PlayerType.White : Player.PlayerType.Black;
        // make sure pawn cannot move backwards
        if (mult*end.r > mult*beg.r)
            return false;

        // simple move
        else if (Math.abs(beg.c - end.c) == 1 && Math.abs(beg.r - end.r) == 1) {
            board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
            board.grid[beg.r][beg.c] = null;
            return true;
        }

        // kill code
        else if (Math.abs(beg.c - end.c) == 2 && Math.abs(beg.r - end.r) == 2) {
            // South-West
            if (beg.c < end.c &&
                    beg.r < end.r &&
                    board.grid[beg.r + 1][beg.c + 1].getPlayerType() == otherPlayerType &&
                    thisPlayerType == Player.PlayerType.White) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r + 1][beg.c + 1] = null;
                return true;
            }
            else if (beg.c < end.c &&
                    beg.r > end.r &&
                    board.grid[beg.r - 1][beg.c + 1].getPlayerType() == otherPlayerType &&
                    thisPlayerType == Player.PlayerType.Black) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r - 1][beg.c + 1] = null;
                return true;
            }
            else if (beg.c > end.c &&
                    beg.r < end.r &&
                    board.grid[beg.r + 1][beg.c - 1].getPlayerType() == otherPlayerType &&
                    thisPlayerType == Player.PlayerType.White) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[beg.r + 1][beg.c - 1] = null;
                return true;
            }
            else if (beg.c > end.c &&
                    beg.r > end.r &&
                    board.grid[beg.r - 1][beg.c - 1].getPlayerType() == otherPlayerType &&
                    thisPlayerType == Player.PlayerType.Black) {
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