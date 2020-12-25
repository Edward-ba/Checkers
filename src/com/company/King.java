package com.company;

public class King extends Token {
    King(CheckersBoard board, Player.PlayerType type)
    {
        super(board, type);
    }

    @Override
    public boolean move(Coordinates beg, Coordinates end)
    {

        // make sure pawn cannot move backwards and col value must change by one
        if (Math.abs(beg.c - end.c) != Math.abs(beg.r - beg.c))
            return false;

        if (beg.r > end.r && beg.c > end.c) {
            for (int i = beg.r; i > end.r;) {
                for (int j = beg.c; j > end.c; --i, --j) {
                    if (board.grid[i][j] != null) {
                        return false;
                    }
                }
            }
        }

        if (beg.r > end.r && beg.c < end.c) {
            for (int i = beg.r; i > end.r;) {
                for (int j = beg.c; j < end.c; --i, ++j) {
                    if (board.grid[i][j] != null) {
                        return false;
                    }
                }
            }
        }

        if (beg.r < end.r && beg.c > end.c) {
            for (int i = beg.r; i < end.r;) {
                for (int j = beg.c; j > end.c; ++i, --j) {
                    if (board.grid[i][j] != null) {
                        return false;
                    }
                }
            }
        }

        if (beg.r < end.r && beg.c < end.c) {
            for (int i = beg.r; i < end.r;) {
                for (int j = beg.c; j < end.c; ++i, ++j) {
                    if (board.grid[i][j] != null) {
                        return false;
                    }
                }
            }
        }

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