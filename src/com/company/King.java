package com.company;

public class King extends Token {
    King(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    /**
     *
     * @param beg
     * @param end
     * @return
     */
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

        // Kill Code
        Coordinates mid = new Coordinates((beg.r + end.r) / 2, (beg.c + end.c) / 2);
        if (Math.abs(beg.c - end.c) == 2 &&
                Math.abs(beg.r - end.r) == 2 &&
                board.grid[mid.r][mid.c].getPlayerType() == otherPlayerType) {

            // Check White pawns are are going south and black players are going north
            board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
            board.grid[beg.r][beg.c] = null;
            board.grid[mid.r][mid.c] = null;
            return true;
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