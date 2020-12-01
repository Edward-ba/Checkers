package com.company;
import java.lang.*;

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

    Board() {
        resetBoard();
    }

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

    public boolean move(Coordinates c1, Coordinates c2, Player player) {
        BoardPiece thisPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.Black : BoardPiece.White;
        BoardPiece kingOfThisPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.BlackKing : BoardPiece.WhiteKing;
        BoardPiece otherPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.White : BoardPiece.Black;
        BoardPiece kingOfOtherPiece = (player.getPlayerType() == Player.PlayerType.Black) ? BoardPiece.WhiteKing : BoardPiece.BlackKing;


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

        if (grid[c1.r][c1.c] == thisPiece) {
            if ((Math.abs(c1.c - c2.c) == 1) &&
                    ((player.getPlayerType() == Player.PlayerType.Black
                            && c1.r - c2.r == 1) ||
                            (player.getPlayerType() == Player.PlayerType.White
                                    && c2.r - c1.r == 1))) {
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
        if (grid[c1.r][c1.c] == kingOfThisPiece) {
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

    public boolean checkWinner() {
        boolean white = false;
        boolean black = false;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] == BoardPiece.White || grid[i][j] == BoardPiece.WhiteKing)
                    white = true;
                else if (grid[i][j] == BoardPiece.Black || grid[i][j] == BoardPiece.BlackKing)
                    black = true;
            }
        }
        if (!white) {
            System.out.println("Black wins");
            return true;
        }
        else if (!black) {
            System.out.println("White wins");
            return true;
        }
        return false;
    }
}
