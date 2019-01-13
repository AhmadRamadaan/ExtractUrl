package com.principle.testing;

import com.principle.extraction.ExtractionManager;
import com.principle.extraction.FileManager;
import com.principle.network.UrlManager;

import java.io.InputStream;
import java.util.Scanner;

public class Manager {

    public Manager(String url) {
        //get connection to read url file
        InputStream inputStream = UrlManager.getConnection(url);

        int exitProgram = 2;
        do {
            Scanner input = new Scanner(System.in);


            System.out.println("choose type to start extraction : \n" +
                    "press 1 : to extract by 'Apache Tika'\n" +
                    "press 2 : to extract by 'Regex'\n");

            int chooise = input.nextInt();
            //extract by regex method
            if (chooise == 1) {

                System.out.println("press 1 : if you need write results in file ");
                System.out.println("press 2 : otherwise ");
                int i = input.nextInt();

                //extract by tika liberary
                String results = ExtractionManager.extractByTika(inputStream);

                //write results in other file
                if (i == 1) FileManager.writer(results);

                //print results in console
                System.out.println(results);

                /////////////////////////////////////////////////////////////

                //invaild input from user
            } else if (chooise == 2) {

                //read Url file
                String results = FileManager.reader(inputStream);

                System.out.println("press 1 : if you need write results in file ");
                System.out.println("press 2 : otherwise ");
                int i = input.nextInt();

                //write results in file
                if (i == 1) FileManager.writer(results);
                //print results
                String textByRegx = ExtractionManager.extractTextByRegx(results);
                System.out.println(textByRegx);

                ///////////////////////////////////////////////////////////

                //extract by Apache Tika liberary
            } else {
                System.out.println(" input not vaild ..");
            }

            System.out.println("press 0 : to exit this URL Page and write another URL ");
            System.out.println("press 1 : to show list again ..");
            exitProgram = input.nextInt();

        } while (exitProgram != 0);
    }
}
