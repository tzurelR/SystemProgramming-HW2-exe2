package com.company;


import java.util.Scanner;

// This class is for the user with the thread:
public class UserGame extends Game{
    public static Scanner sc = new Scanner(System.in);

    public UserGame(Enum x, Enum o) {
        super();

    }


    // x = computerThread
    // o = user

    // 1. Method for the computer(the threadX):
    public synchronized void playOfThreadX() {

        while(!isGameOver()) {


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("throw from sleep in playOfThreadX");
            }

            // check if O put, and if not wait:
            while (!getEnumO().isPlayed()) {
                try {
                    System.out.println("\nNow computer wait for user to play \n");
                    wait();
                    if (isGameOver())
                        break;
                } catch (InterruptedException e) {
                    System.err.println("throw from UserGame in playOfThreadX");
                }
            }

            if (!isGameOver()) {
                // threadX put x:
                if (!getEmptyCellsArr().isEmpty()) {
                    int random = (int) ((Math.random() * getEmptyCellsArr().size()));

                    System.out.println("It's the turn of thread2:");
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
    public synchronized void playOfUserO() {


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
                    System.out.println("\nNow user wait for computer to play \n");
                    wait();
                    if (isGameOver())
                        break;
                } catch (InterruptedException e) {
                    System.err.println("throw from UserGame in playOfUserO");
                }
            }

            if (!isGameOver()) {
                // threadX put x:
                Cell empty = getEmptySingleCell();
                int row = 0;
                int col = 0;
                if (empty != null) {
                    System.out.println("It's the turn of user: put the row, and col that you want to put o");
                    do {
                        row = sc.nextInt();
                        col = sc.nextInt();

                        if (row > 2 || col > 2 || !isCellEmpty(row, col))
                            System.out.println("this indexes aren't in the board or this place is full, put again! ");
                    } while (!(row <= 2 && col <= 2) || !isCellEmpty(row, col));

                    setGameBoard(row, col, 'o');
                }
                // update emptyCellArr
                // check who remove:
                for(int i = 0; i < getEmptyCellsArr().size(); i++) {
                    if(getEmptyCellsArr().get(i).getRow() == row && getEmptyCellsArr().get(i).getCol() == col) {
                        getEmptyCellsArr().remove(i);
                    }
                }


                printBoard();

                // update enum and notify all
                getEnumO().setPlayed(true);
                getEnumX().setPlayed(false);
            }
            notifyAll();
        }
    }


}
