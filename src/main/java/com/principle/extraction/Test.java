package com.principle.extraction;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        /**
         * url of page founded
         */

        String url = "https://www.gotoquiz.com/web-coding/programming/java-programming/how-to-extract-titles-from-web-pages-in-java/";
        /**
         * url of page not founded (by add 5 to end of link)
         */
//        String url = "https://www.gotoquiz.com/web-coding/programming/java-programming/how-to-extract-titles-from-web-pages-in-java5/";

        DownloaderManager downloader = new DownloaderManager();
        ExtractorManager extractor = new ExtractorManager();

        try {
            /**
             * download file by URL
             */
            File file1 = downloader.downloadFile(url);

            /**
             * extract (dowloaded file) and print the output in console
             */
            extractor.extractLocalFile(file1);

            /**
             * extract (dowloaded file) and print the output in Other output file
             */
            extractor.extractLocalFile(file1, "outputFile1.txt");


        } catch (Exception e) {
            System.out.println("there is one exception occured ..");
            e.printStackTrace();
        }
    }

}
