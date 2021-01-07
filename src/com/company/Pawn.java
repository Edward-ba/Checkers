package com.company;

public class Pawn extends Token {
    /**
     * constructor
     * @param board
     * @param type
     */
    Pawn(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    /**
     * Move & Kill logic for Pawn
     * @param beg begin location
     * @param end end location
     * @return Whether or not it works
     */
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
        // get location of enemy piece
        Coordinates mid = new Coordinates((beg.r + end.r) / 2, (beg.c + end.c) / 2);
        if (Math.abs(beg.c - end.c) == 2 &&
                Math.abs(beg.r - end.r) == 2 &&
                board.grid[mid.r][mid.c].getPlayerType() == otherPlayerType) {
            // Check White pawns are are going south and black players are going north
            if ((beg.r < end.r && thisPlayerType == Player.PlayerType.White) ||
                    (beg.r > end.r && thisPlayerType == Player.PlayerType.Black)) {
                board.grid[end.r][end.c] = board.grid[beg.r][beg.c];
                board.grid[beg.r][beg.c] = null;
                board.grid[mid.r][mid.c] = null;
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

    @Override
    public int score() {
        return 1;
    }
}