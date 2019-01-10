package com.principle.extraction;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

//        try {

        ExtractionManager manager = new ExtractionManager();

        String url = "https://edition.cnn.com/";
        manager.downloadFile2(url);
        File downloadedFile = manager.downloadFile(url);

        manager.extractLocalFile(downloadedFile, "outputFile.txt");

//        }catch (Exception e ){
//            System.out.println("there is one exception occured ..");
//        }
    }

}
