package com.company;

import java.util.ArrayList;

public /*abstract*/ class Game {

    private char[][] gameBoard;
    private ArrayList<Cell> emptyCells;
    private Enum x;
    private Enum o;
    private String winner;


    public Game() {
        gameBoard = new char[3][3];
        emptyCells = new ArrayList<>();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';

                // initialize empty cells and then just remove:
                emptyCells.add(new Cell(i, j));
            }
        }

        x = new Enum('x');
        o = new Enum('o');

        this.printBoard();
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public char getVal(int row, int col) {
        if (row < 3 && col < 3) {
            return gameBoard[row][col];
        } else {
            System.out.println("getVal -> can't get because the index are invalid!");
        }
        return ' ';
    }

    public void setGameBoard(int row, int col, char val) {
        // check that this cell is empty!
        if (row < 3 && col < 3 && isCellEmpty(row, col)) {
            gameBoard[row][col] = val;
        } else {
            System.out.println("setGameBoard -> can't set because the index are invalid or this cell is full!");
        }
    }

    public void printBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (j != gameBoard[i].length - 1)
                    System.out.print(gameBoard[i][j] + "\t|\t");
                else
                    System.out.print(gameBoard[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("--------------------END BOARD--------------------");
    }

    public void setEmptyCells(int position) {
        emptyCells.remove(position);
    }

    public Cell getEmptySingleCell() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    public boolean isBoardEmpty() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCellEmpty(int row, int col) {
        if (gameBoard[row][col] == ' ') {
            return true;
        } else
            return false;
    }


    public Enum getTurn() {
        if (x.isPlayed()) {
            return o;
        } else {
            return x;
        }
    }

    public Enum getEnumX() {
        return x;
    }

    public Enum getEnumO() {
        return o;
    }

    // this method return true if game over:
    // change the boolean PROBLEM!!!
    public boolean isGameOver() {
        int counterX = 0;
        int counterO = 0;
        int i = 0;
        int c = 0;

        // check rows
        while (i < 3) {
            for (c = 0; c < gameBoard.length; c++) {
                if (gameBoard[i][c] == 'x') {
                    counterX += 1;
                }
                if (gameBoard[i][c] == 'o') {
                    counterO += 1;
                }
            }

            if (counterO == 3) {
                winner = "O";
                return true;
            }
            if (counterX == 3) {
                winner = "X";
                return true;
            }
            counterX = 0;
            counterO = 0;
            i++;
        }

        i = 0;
        c = 0;
        // check cols:
        while (c < 3) {

            for (i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i][c] == 'x') {
                    counterX += 1;
                }
                if (gameBoard[i][c] == 'o') {
                    counterO += 1;
                }
            }

            if (counterO == 3) {
                winner = "O";
                return true;
            }
            if (counterX == 3) {
                winner = "X";
                return true;
            }
            counterX = 0;
            counterO = 0;
            c++;
        }

        int counterDiagonalO = 0;
        int counterDiagonalX = 0;


        // check diagonals:
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (row == col) {
                    if (gameBoard[row][col] == 'x') {
                        counterX += 1;
                    }
                    if (gameBoard[row][col] == 'o') {
                        counterO += 1;
                    }
                }

                if((row + col) == 2) {
                    if (gameBoard[row][col] == 'x') {
                        counterDiagonalX += 1;
                    }
                    if (gameBoard[row][col] == 'o') {
                        counterDiagonalO += 1;
                    }
                }
            }
        }
        if (counterO == 3 || counterDiagonalO == 3) {
            winner = "O";
            return true;
        }
        if (counterX == 3 || counterDiagonalX == 3) {
            winner = "X";
            return true;
        }


        // check if the board is full:
        if (isBoardFull()) {
            winner = "the board is full no one";
            return true;
        }

        return false;


    }

    public String getWinner() {
        return winner;
    }

    public ArrayList<Cell> getEmptyCellsArr() {
        return emptyCells;
    }
}
