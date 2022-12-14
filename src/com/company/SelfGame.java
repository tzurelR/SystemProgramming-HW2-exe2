package com.company;

import java.util.ArrayList;

public class SelfGame extends Game {


    public SelfGame(Enum x, Enum o) {
        super();

    }


    // x = thread1
    // o = thread2

    // 1. Method for the computer(the threadX):
    public synchronized void playOfThread1() {

        while(!isGameOver()) {


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("throw from sleep in playOfThreadX");
            }

            // check if thread1 put, and if not wait:
            while (!getEnumO().isPlayed()) {
                try {
                    System.out.println("\nNow thread1 wait for thread2 to play \n");
                    wait();
                    if (isGameOver())
                        break;
                } catch (InterruptedException e) {
                    System.err.println("throw from UserGame in playOfThreadX");
                }
            }

            if (!isGameOver()) {

                // thread1 put x:
                // with random thread1 achieve some empty place in arrayList:
                if (!getEmptyCellsArr().isEmpty()) {
                    int random = (int) ((Math.random() * getEmptyCellsArr().size()));

                    System.out.println("It's the turn of thread1:");
                    int row = getEmptyCellsArr().get(random).getRow();
                    int col = getEmptyCellsArr().get(random).getCol();
                    setGameBoard(row, col, 'x');

                    // update emptyCellArr
                    getEmptyCellsArr().remove(random);


                    printBoard();

                    // update enum and notify all
                    getEnumX().setPlayed(true);
                    getEnumO().setPlayed(false);
                }
            }
            notifyAll();
        }
    }


    // 2. Method for the computer(the thread):
    public synchronized void playOfThread2() {


        while(!isGameOver()) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("throw from sleep in playOfUserO");
            }

            // check if X put, and if not wait:
            while (!getEnumX().isPlayed()) {

                // for the first time of userO, the user start!
                if (isBoardEmpty()) {
                    break;
                }

                try {
                    System.out.println("\nNow thread2 wait for thread1 to play \n");
                    wait();
                    if (isGameOver())
                        break;
                } catch (InterruptedException e) {
                    System.err.println("throw from UserGame in playOfUserO");
                }
            }

            if (!isGameOver()) {
                // thread2 put o:
                // with random thread1 achieve some empty place in arrayList:
                if (!getEmptyCellsArr().isEmpty()) {
                    int random = (int) ((Math.random() * getEmptyCellsArr().size()));

                    System.out.println("It's the turn of thread2:");
                    int row = getEmptyCellsArr().get(random).getRow();
                    int col = getEmptyCellsArr().get(random).getCol();
                    setGameBoard(row, col, 'o');

                    // update emptyCellArr
                    getEmptyCellsArr().remove(random);


                    printBoard();

                    // update enum and notify all
                    getEnumX().setPlayed(false);
                    getEnumO().setPlayed(true);
                }
            }
            notifyAll();
        }
    }



}
