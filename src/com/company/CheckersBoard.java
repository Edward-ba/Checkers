package com.company;

public class CheckersBoard {
    final int size = 8;
    final public Token[][] grid = new Token[size][size];

    CheckersBoard() {
        // when the board is created reset the board
        resetBoard();
    }

    // prints the board
    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < size; ++i)
            System.out.print("  " + i + " ");
        System.out.println();
        System.out.print("  -");
        for (int i = 0; i < size; ++i)
            System.out.print(" - -");
        System.out.println();
        for (int i = 0; i < size; ++i) {
            System.out.print(i + " | ");
            for (int j = 0; j < size; ++j)
            {
                if (grid[i][j] != null)
                    System.out.print(grid[i][j].print() + " | ");
                else
                    System.out.print("  | ");
            }
            System.out.println();
            if (i != size - 1)
                System.out.print("  |");
            else
                System.out.print("  -");
            for (int j = 0; j < size; ++j) {
                System.out.print(" - ");
                if (i == size - 1)
                    System.out.print("-");
                else if (j == size - 1)
                    System.out.print("|");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }

    // resets the board
    public void resetBoard() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if ((i < 3 && i % 2 == 0 && j % 2 == 1) || (i < 3 && i % 2 == 1 && j % 2 == 0))
                    grid[i][j] = new Pawn(this, Player.PlayerType.White);
                else if ((i > 4 && i % 2 == 0 && j % 2 == 1) || (i > 4 && i % 2 == 1 && j % 2 == 0))
                    grid[i][j] = new Pawn(this, Player.PlayerType.Black);
                else
                    grid[i][j] = null;
            }
        }
    }

    public boolean checkWinner() {
        return false;
    }


    public boolean move(Coordinates beg, Coordinates end, Player player) {
        if ((beg.r < 0) ||
                (beg.c < 0) ||
                (beg.r > size - 1) ||
                (beg.c > size - 1) ||
                (end.r < 0) ||
                (end.c < 0) ||
                (end.r > size - 1) ||
                (end.c > size - 1) ||
                (grid[beg.r][beg.c] == null) ||
                (grid[beg.r][beg.c].getPlayerType() != player.getPlayerType()) ||
                (grid[end.r][end.c] != null))
            return false;

        return grid[beg.r][beg.c].move(beg, end);
    }
}
