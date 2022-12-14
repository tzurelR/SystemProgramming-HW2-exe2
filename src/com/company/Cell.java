package com.company;

public class Cell {
    private final int row;
    private final int col;

    public Cell(int r, int c) {
        row = r;
        col = c;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
