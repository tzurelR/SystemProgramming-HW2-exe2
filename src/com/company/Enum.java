package com.company;

public class Enum {

    private final char XorO;
    private boolean isPlayed;

    public Enum(char c) {
        if(c == 'x') {
            XorO = c;
        } else {
            XorO = 'o';
        }
        isPlayed = false;
    }

    public char getXorO() {
        return XorO;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}
