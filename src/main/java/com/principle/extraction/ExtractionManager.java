package com.principle.extraction;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;

public class ExtractionManager {

    public File downloadFile(String url) {

        boolean isHTMLType = new UrlInfo().checkHtmlContentType(url);
        if (!isHTMLType) {
            System.out.println("the content type is not html");
            return null;
        } else {
            //get title of document(page)
            String titlePage = getDocument(url).title();
            //create ouput file and named as title of document(page)
            File outputFile = new File(titlePage + ".html");

            URL url1 = null;
            try {
                url1 = new URL(url);
                //create input stream for read document from URL
                BufferedInputStream inputStream = new BufferedInputStream(url1.openStream());

                //create output stream for write document from reader
                FileOutputStream outstream = new FileOutputStream(outputFile);
                //transfer data from input stream to output stream
                transferData(inputStream, outstream);

                //catch clause
            } catch (MalformedURLException e) {
                System.out.println("connection faild ..");
            } catch (FileNotFoundException e) {
                System.out.println("output file doesn't exist");
            } catch (IOException e) {
                System.out.println("input stream can not read ");
//            e.printStackTrace();
            }

            return outputFile;
        }
    }

    public void transferData(BufferedInputStream inputStream, FileOutputStream outstream) {
        byte[] dataBuffer = new byte[1000];

        int bytesRead;
        try {
            bytesRead = inputStream.read(dataBuffer, 0, 1000);
            while (bytesRead != -1) {
                outstream.write(dataBuffer, 0, bytesRead);
                bytesRead = inputStream.read(dataBuffer);
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
    }

    public void extractLocalFile(File inputFile, String outputFilePath) {
        if (outputFilePath == null) outputFilePath = "outputFile.txt";

        File outputFile = new File(outputFilePath);
        OutputStream outputStream = null;
        FileInputStream inputstream = null;
        try {
            outputStream = new FileOutputStream(outputFile);
            // Any Input_stream object that contains the content of the file
            inputstream = new FileInputStream(inputFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * Creates a content handler : that writes XHTML body character events
         * to an internal string buffer. Tika passes content of the document
         * (as: XHTML) to this handler . //and detecting the file type
         */
        BodyContentHandler handler = new BodyContentHandler();
        BodyContentHandler handlerFile = new BodyContentHandler(outputStream);

        /**
         * If we consider ( an audio file, the artist name, album name, title )
         * comes under Meta_data
         */
        Metadata metadata = new Metadata();


        ParseContext pcontext = new ParseContext();

        //Html parser : turn the input document to HTML SAX events
        HtmlParser htmlparser = new HtmlParser();

        try {
            htmlparser.parse(inputstream, handlerFile, metadata, pcontext);

            inputstream.close();
            inputstream = new FileInputStream(inputFile);
            htmlparser.parse(inputstream, handler, metadata, pcontext);

            //print data of <body>
            System.out.println("Content Body of the document:" + handler.toString());

        } catch (IOException e) {
            System.out.println("the document stream could not be read");
        } catch (SAXException e) {
            //sax contains error of XML parser
            e.printStackTrace();
        } catch (TikaException e) {
            System.out.println("the document could not be parsed");
            e.printStackTrace();
        } finally {
            if (inputstream != null) {
                try {
                    inputstream.close();
                } catch (IOException e) {
                    System.out.println("can not close input stream ..");
                }
            }
        }

    }

    public Document getDocument(String url) {
        Document document = null;
        try {
            //Get Document object after parsing the html from given url.
            System.out.println("get status =" + getStatusCode(url));
            document = Jsoup.connect(url).get();

        } catch (MalformedURLException formedUrl) {
            System.out.println("the request URL is not a HTTP or HTTPS URL, or is otherwise malformed ");
        } catch (HttpStatusException status) {
            System.out.println("page not found");
        } catch (UnsupportedMimeTypeException mime) {
            System.out.println("the content type of Url page is not ('text/html') and the response mime type is not supported and those errors are not ignored");
        } catch (SocketTimeoutException socket) {
            System.out.println(" the connection times out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public int getStatusCode(String url) {
        int statusCode = 404;
        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).execute();
            statusCode = response.statusCode();
            System.out.println("status code = " + statusCode);

        } catch (MalformedURLException formedUrl) {
            System.out.println("the request URL is not a HTTP or HTTPS URL, or is otherwise malformed ");
        } catch (HttpStatusException status) {
            System.out.println("page not found");
        } catch (UnsupportedMimeTypeException mime) {
            System.out.println("the response mime type is not supported and those errors are not ignored");
        } catch (SocketTimeoutException socket) {
            System.out.println(" the connection times out");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return statusCode;
    }

}
