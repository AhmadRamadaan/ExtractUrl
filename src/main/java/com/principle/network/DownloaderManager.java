/**
 * this Class not used in the current time ..
 */

//package com.principle.network;
//
//
//import com.principle.extraction.FileManager;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class DownloaderManager {
//
//    /**
//     * download file from URL with out any liberary
//     *
//     * @param url
//     * @return File
//     */
//
//    public File downloadFile(String url) {
//
//        UrlInfoManager urlInfo = new UrlInfoManager();
//
//        //check the content Type of URL(page)
//        boolean isHTMLType = urlInfo.checkHtmlContentType(url);
//        //if is not html return null
//        if (!isHTMLType) {
//            System.out.println("the content type is not html");
//            return null;
//
//        } else {
//            //get title of document(page)
//            String titlePage = urlInfo.getDocument(url).title();
//            //named ouput file : with Title of page
//            String outputFilePath = titlePage + ".html";
//            File file = null;
//            URL url1 = null;
//            try {
//                url1 = new URL(url);
//                //create input stream for read document from URL
//                BufferedInputStream inputStream = new BufferedInputStream(url1.openStream());
//
//                //transfer data from input stream to output stream
//                file = FileManager.writeInFile(inputStream, outputFilePath);
//
//                //catch clause ..
//            } catch (MalformedURLException e) {
//                System.out.println("connection faild ..");
//            } catch (FileNotFoundException e) {
//                System.out.println("output file doesn't exist");
//            } catch (IOException e) {
//                System.out.println("input stream can not read ");
////            e.printStackTrace();
//            }
//            return file;
//        }
//    }
//
//}
