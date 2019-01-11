package com.principle.extraction;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {


    /**
     * transfer data from input stream to output stream
     *
     * @param inputStream
     */
    public static File writeInFile(BufferedInputStream inputStream, String outputFilePath) {

        //create ouput file
        File outputFile = new File(outputFilePath);
        //create output stream for write document from reader
        FileOutputStream outstream = null;

        byte[] dataBuffer = new byte[1000];
        int bytesRead;
        try {
            outstream = new FileOutputStream(outputFile);
            /**
             * Reads 1000 bytes from this (input stream) into
             * the specified (dataBuffer) array, starting at the given offset.
             *
             */
            bytesRead = inputStream.read(dataBuffer, 0, 1000);
            while (bytesRead != -1) {
                outstream.write(dataBuffer, 0, bytesRead);
                bytesRead = inputStream.read(dataBuffer, 0, 1000);
            }

        } catch (IOException e) {
            System.out.println("connection faild ..");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    outstream.flush();
                    outstream.close();
                } catch (IOException e) {
                    System.out.println("can not close input stream ..");
                }
            }
        }
        return outputFile;
    }

}
