package com.principle.extraction;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

//        try {

        ExtractionManager manager = new ExtractionManager();

        String url = "https://stackoverflow.com/questions/25244475/how-to-configure-intellij-also-android-studio-redo-shortcut-to-ctrly-instead";
        File downloadedFile = manager.downloadFile(url);

        manager.extractLocalFile(downloadedFile, null);

//        }catch (Exception e ){
//            System.out.println("there is one exception occured ..");
//        }
    }

}
