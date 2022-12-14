package com.company;

public abstract class Player {
    // this class just present if the player is x or o:
    private final Enum e;

    public Player(Enum e) {
        this.e = e;

    }

    public Enum getEnum() {
        return e;
    }


}
