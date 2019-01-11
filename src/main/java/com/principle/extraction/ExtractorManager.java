package com.principle.extraction;

import org.xml.sax.SAXException;

import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;

public class ExtractorManager {

    /**
     * after download file by URL
     * this method will extract body of file in console
     *
     * @param inputFile
     */
    public void extractLocalFile(File inputFile) {

        FileInputStream inputstream = null;
        try {
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

        /**
         * If we consider ( an audio file, the artist name, album name, title )
         * comes under Meta_data
         */
        Metadata metadata = new Metadata();

        //Parse context. Used to pass context information to Tika parsers.
        ParseContext pcontext = new ParseContext();

        //Html parser : turn the input document to HTML SAX events
        HtmlParser htmlparser = new HtmlParser();

        try {
            //pcontext take data from inputstream to handler and metadata
            htmlparser.parse(inputstream, handler, metadata, pcontext);
            //print output in console
            System.out.println(handler.toString());

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

    /**
     * after download file by URL(input file param)
     * this method will extract body of file in another File(output file path param)
     * this method extract body of inputFile.html in other file
     *
     * @param inputFile
     * @param outputFilePath
     */
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

}
