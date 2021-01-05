package com.company;
import java.lang.*;
import java.util.*;

// no longer use this class
public class Board {
    final int size = 8;

    public enum BoardPiece {
        Empty {
            public String toString() { return " "; }
        },
        White {
            public String toString() { return "w"; }
        },
        Black {
            public String toString() { return "b"; }
        },
        WhiteKing {
            public String toString() { return "W"; }
        },
        BlackKing{
            public String toString() { return "B"; }
        }
    }

    final private BoardPiece[][] grid = new BoardPiece[size][size];

    // constructor
    Board() {
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
                System.out.print(grid[i][j] + " | ");
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
                    grid[i][j] = BoardPiece.White;
                else if ((i > 4 && i % 2 == 0 && j % 2 == 1) || (i > 4 && i % 2 == 1 && j % 2 == 0))
                    grid[i][j] = BoardPiece.Black;
                else
                    grid[i][j] = BoardPiece.Empty;
            }
        }
    }

    /**
     *
     * @param c1 starting coordinates
     * @param c2 end coordinates
     * @param player which player is moving the pieces
     * @return true if it works
     */
    public boolean move(Coordinates c1, Coordinates c2, Player player) {
        // takes the player and gets the piece types that they own
        BoardPiece thisPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.Black : BoardPiece.White;
        BoardPiece kingOfThisPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.BlackKing : BoardPiece.WhiteKing;
        BoardPiece otherPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.White : BoardPiece.Black;
        BoardPiece kingOfOtherPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.WhiteKing : BoardPiece.BlackKing;

        // checks for most of the errors
        if ((c1.r < 0) ||
                (c1.c < 0) ||
                (c1.r > size - 1) ||
                (c1.c > size - 1) ||
                (c2.r < 0) ||
                (c2.c < 0) ||
                (c2.r > size - 1) ||
                (c2.c > size - 1) ||
                (grid[c1.r][c1.c] == BoardPiece.Empty) ||
                (grid[c1.r][c1.c] == otherPiece) ||
                (grid[c1.r][c1.c] == kingOfOtherPiece) ||
                (grid[c2.r][c2.c] != BoardPiece.Empty))
            return false;

        // check if the starting point has the correct piece
        if (grid[c1.r][c1.c] == thisPiece) {
            // check if the columns of the two coordinates are one space apart and if the direction the pieces are moving is correct then move the piece
            if ((Math.abs(c1.c - c2.c) == 1) &&
                    ((player.getPlayerType() == Player.PlayerType.Black && c1.r - c2.r == 1) ||
                            (player.getPlayerType() == Player.PlayerType.White && c2.r - c1.r == 1))) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c2.r][c2.c] = thisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
            // check if the coordinates are two spaces apart diagonally to the left and there is one of the other players pieces between them
            else if (c1.r - 2 == c2.r &&
                    c1.c - 2 == c2.c &&
                    (grid[c1.r - 1][c1.c - 1] == otherPiece || grid[c1.r - 1][c1.c - 1] == kingOfOtherPiece)) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c1.r - 1][c1.c - 1] = BoardPiece.Empty;
                grid[c2.r][c2.c] = thisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
            // same as above only to the left
            else if (c1.r + 2 == c2.r &&
                    c1.c - 2 == c2.c &&
                    (grid[c1.r + 1][c1.c - 1] == otherPiece || grid[c1.r + 1][c1.c - 1] == kingOfOtherPiece)) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c1.r + 1][c1.c - 1] = BoardPiece.Empty;
                grid[c2.r][c2.c] = thisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
        }
        // checking if instead of a normal piece on the starting piece and there is a king there
        if (grid[c1.r][c1.c] == kingOfThisPiece) {
            // moving around the board
            if (Math.abs(c1.r - c2.r) == Math.abs(c1.c - c2.c)) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c2.r][c2.c] = kingOfThisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
            // killing enemy pieces
            else if (c1.r - 2 == c2.r && c1.c - 2 == c2.c && (grid[c1.r - 1][c1.c - 1] == otherPiece || grid[c1.r - 1][c1.c - 1] == kingOfOtherPiece)) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c1.r - 1][c1.c - 1] = BoardPiece.Empty;
                grid[c2.r][c2.c] = thisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
            else if (c1.r + 2 == c2.r && c1.c - 2 == c2.c && (grid[c1.r + 1][c1.c - 1] == otherPiece || grid[c1.r + 1][c1.c - 1] == kingOfOtherPiece)) {
                grid[c1.r][c1.c] = BoardPiece.Empty;
                grid[c1.r + 1][c1.c - 1] = BoardPiece.Empty;
                grid[c2.r][c2.c] = thisPiece;
                for (int i = 0; i < size; ++i) {
                    if (grid[0][i] == BoardPiece.Black)
                        grid[0][i] = BoardPiece.BlackKing;
                    if (grid[size - 1][i] == BoardPiece.White)
                        grid[size - 1][i] = BoardPiece.WhiteKing;
                }
                return true;
            }
        }
        return false;
    }
}