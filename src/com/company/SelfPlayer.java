package com.company;

public class SelfPlayer extends Player implements Runnable{

    private SelfGame selfGame;
    public SelfPlayer(Enum e, SelfGame g) {
        super(e);
        selfGame = g;
    }



    @Override
    public void run() {

        //Here to check if it is X or O:
        if(this.getEnum().getXorO() == 'x') {
            selfGame.playOfThread1();
        } else {
            selfGame.playOfThread2();
        }

    }

}
