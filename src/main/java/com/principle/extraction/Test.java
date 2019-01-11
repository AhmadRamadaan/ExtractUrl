package com.principle.extraction;

import java.io.File;

public class Test {

    public static void main(String[] args) {

//        String url = "https://edition.cnn.com/";
        String url = "https://stackoverflow.com/questions/10518972/open-a-connection-with-jsoup-get-status-code-and-parse-document";

        DownloaderManager downloader = new DownloaderManager();
        ExtractorManager extractor = new ExtractorManager();

        try {
            /**
             * download file by URL
             * you can use any of 3 below methods
             */
            File file1 = downloader.downloadFile(url);
//            File file2 = downloader.downloadFilebyAsync(url);
//            File file3 = downloader.downloadFileByNIO(url);

            /**
             * extract (dowloaded file) and print the output in console
             * you can use any of 3 below methods
             */
            extractor.extractLocalFile(file1);
//            extractor.extractLocalFile(file2);
//            extractor.extractLocalFile(file3);

            /**
             * extract (dowloaded file) and print the output in Other(output file)
             * you can use any of 3 below methods
             */
            extractor.extractLocalFile(file1, "outputFile1.txt");
//            extractor.extractLocalFile(file1,"outputFile2.txt");
//            extractor.extractLocalFile(file1,"outputFile3.txt");


        } catch (Exception e) {
            System.out.println("there is one exception occured ..");
        }
    }

}
