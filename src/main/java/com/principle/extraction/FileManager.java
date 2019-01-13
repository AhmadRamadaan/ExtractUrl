package com.principle.extraction;

import java.io.*;

public class FileManager {

    public static String reader(InputStream inputStream) {
        BufferedReader reader = null;
        String line = null;
        StringBuilder stringBuilder = null;

        try {
            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            //catch cluase
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    public static void writer(String buffer) {

        try {
            PrintWriter out = new PrintWriter("Results.html");
            out.println(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    /**
     * this Method not used in the current time ..
     * transfer data from input stream to output stream
     *
     * @param inputStream
     */
//    public static File writeInFile(BufferedInputStream inputStream, String outputFilePath) {
//
//        //create ouput file
//        File outputFile = new File(outputFilePath);
//        //create output stream for write document from reader
//        FileOutputStream outstream = null;
//
//        byte[] dataBuffer = new byte[1000];
//        int bytesRead;
//        try {
//            outstream = new FileOutputStream(outputFile);
//            /**
//             * Reads 1000 bytes from this (input stream) into
//             * the specified (dataBuffer) array, starting at the given offset.
//             *
//             */
//            bytesRead = inputStream.read(dataBuffer, 0, 1000);
//            while (bytesRead != -1) {
//                outstream.write(dataBuffer, 0, bytesRead);
//                bytesRead = inputStream.read(dataBuffer, 0, 1000);
//            }
//
//        } catch (IOException e) {
//            System.out.println("connection faild ..");
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                    outstream.flush();
//                    outstream.close();
//                } catch (IOException e) {
//                    System.out.println("can not close input stream ..");
//                }
//            }
//        }
//        return outputFile;
//    }

}
