package com.principle.testing;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        int exitProgram = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("enter(paste) the RUL of page :");
            String url = input.next();

//        String url = "https://edition.cnn.com/";
            new Manager(url);

            //
            System.out.println("press 0 : to exit program ");
            System.out.println("press 1 : to enter another URL ..");
            exitProgram = input.nextInt();
        } while (exitProgram != 0);
    }

}
