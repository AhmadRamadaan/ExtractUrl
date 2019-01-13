package com.principle.testing;

import com.principle.extraction.ExtractionManager;
import com.principle.extraction.FileManager;
import com.principle.network.UrlManager;

import java.io.InputStream;
import java.util.Scanner;

public class Manager {

    public Manager(String url) {

        int exitProgram = 2;
        do {
            Scanner input = new Scanner(System.in);

            System.out.println("choose type to start extraction : \n" +
                    "press 1 : to extract by 'regex'\n" +
                    "press 2 : to extract by 'Apache Tika'\n"
            );

            int chooise = input.nextInt();
            InputStream inputStream = UrlManager.getConnection(url);
            if (chooise == 1) {

                String results = FileManager.reader(inputStream);
                System.out.println("press 1 : if you need write results in file ");
                System.out.println("press 2 : otherwise ");
                int i = input.nextInt();
                if (i == 1) FileManager.writer(results);
                //print results
                String textByRegx = ExtractionManager.extractTextByRegx(results);
                System.out.println(textByRegx);

            } else if (chooise == 2) {

                String results = ExtractionManager.extractByTika(inputStream);
                //write results in other file
                System.out.println("press 1 : if you need write results in file ");
                System.out.println("press 2 : otherwise ");
                int i = input.nextInt();
                //print results in console
                if (i == 1) FileManager.writer(results);
                System.out.println(results);

            } else {
                System.out.println("not vaild ..");
            }

            System.out.println("press 0 : to exit program");
            System.out.println("press 2 : to show list again ..");
            exitProgram = input.nextInt();

        } while (exitProgram != 0);
    }
}
