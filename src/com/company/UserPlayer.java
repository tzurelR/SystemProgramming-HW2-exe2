package com.company;

public class UserPlayer extends Player implements Runnable{

    private UserGame userGame;
    public UserPlayer(Enum e, UserGame g) {
        super(e);
        userGame = g;
    }



    @Override
    public void run() {
        //Here to check if it is X or O:
        if(this.getEnum().getXorO() == 'x') {
            userGame.playOfThreadX();
        } else {
            userGame.playOfUserO();
        }

    }
}
