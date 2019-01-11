package com.principle.extraction;

import org.asynchttpclient.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class DownloaderManager {

    /**
     * download file from URL with out any liberary
     *
     * @param url
     * @return File
     */

    public File downloadFile(String url) {

        UrlInfoManager urlInfo = new UrlInfoManager();

        //check the content Type of URL(page)
        boolean isHTMLType = urlInfo.checkHtmlContentType(url);
        //if is not html return null
        if (!isHTMLType) {
            System.out.println("the content type is not html");
            return null;

        } else {
            //get title of document(page)
            String titlePage = urlInfo.getDocument(url).title();
            //named ouput file : with Title of page
            String outputFilePath = titlePage + ".html";
            File file = null;
            URL url1 = null;
            try {
                url1 = new URL(url);
                //create input stream for read document from URL
                BufferedInputStream inputStream = new BufferedInputStream(url1.openStream());

                //transfer data from input stream to output stream
                file = FileManager.writeInFile(inputStream, outputFilePath);

                //catch clause ..
            } catch (MalformedURLException e) {
                System.out.println("connection faild ..");
            } catch (FileNotFoundException e) {
                System.out.println("output file doesn't exist");
            } catch (IOException e) {
                System.out.println("input stream can not read ");
//            e.printStackTrace();
            }
            return file;
        }
    }

    /**
     * download file by (async-http-client) liberary
     *
     * @param url
     */
    public File downloadFilebyAsync(String url) {
        File file = new File("async.html");
        //create an HTTP client:
        AsyncHttpClient client = Dsl.asyncHttpClient();
        try {
            //downloaded content will be placed into a FileOutputStream:
            final FileOutputStream outputStream = new FileOutputStream(file);

            /**
             *  we create an HTTP GET request
             *  and register an AsyncCompletionHandler handler
             *  to process the downloaded content:
             */
            client.prepareGet(url).execute(new AsyncCompletionHandler<FileOutputStream>() {

                @Override
                public State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                        throws Exception {
                    outputStream.getChannel().write(bodyPart.getBodyByteBuffer());
                    return State.CONTINUE;
                }

                @Override
                public FileOutputStream onCompleted(Response response)
                        throws Exception {
                    return outputStream;
                }
            });

            outputStream.flush();
            outputStream.close();
            client.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * download file by (java NIO )package :
     * to transfer bytes between 2 Channels without buffering them into the application memory.
     *
     * @param url
     * @return
     */
    public File downloadFileByNIO(String url) {
        URL url1 = null;
        File outputFile = null;
        try {
            url1 = new URL(url);
            // read the file from our URL, we’ll create a new ReadableByteChannel from the URL stream:
            ReadableByteChannel readableByteChannel = Channels.newChannel(url1.openStream());
            outputFile = new File("NIOfile.html");

            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            FileChannel fileChannel = fileOutputStream.getChannel();

            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }
}
