package com.principle.testing;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("enter the RUL of page :");
        String url = input.next();

//        String url = "https://edition.cnn.com/";
        new Manager(url);
    }

}
