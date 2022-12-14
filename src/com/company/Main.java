package com.company;

import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hey, welcome to X O game! please choose one of the options:\n" +
                "1. You play with thread\n" +
                "2. Two thread will play\n");
        int choose = sc.nextInt();

        if (choose == 1) {
            Enum x = new Enum('x');
            Enum o = new Enum('o');
            UserGame gameUser = new UserGame(x, o);
            // threads->
            UserPlayer computer = new UserPlayer(x, gameUser);
            UserPlayer user = new UserPlayer(o, gameUser);

            Thread tCom = new Thread(computer);
            Thread tUser = new Thread(user);

            tCom.start();
            tUser.start();

            try {
                tCom.join();
                tUser.join();
            } catch (InterruptedException e) {
                System.err.println("throws from join in the main");
            }

            System.out.println("\n-----------------------------" + gameUser.getWinner() + " is the Winner!-----------------------------");
        } else {


            System.out.println("thread1 == x AND thread2 == 0");

            Enum x = new Enum('x');
            Enum o = new Enum('o');
            SelfGame g1 = new SelfGame(x, o);
            // threads:
            SelfPlayer t1 = new SelfPlayer(x, g1);
            SelfPlayer t2 = new SelfPlayer(o, g1);

            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t2);

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                System.err.println("throws from join in the main");
            }

            System.out.println("\n-----------------------------" + g1.getWinner() + " is the Winner!-----------------------------");

        }
    }
}
