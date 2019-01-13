package com.principle.extraction;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.MutableAttributeSet;

public class ExtractionManager {

    public static String extractByTika(InputStream inputStream) {

        String results = null;
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
            htmlparser.parse(inputStream, handler, metadata, pcontext);
            results = handler.toString();

        } catch (IOException e) {
            System.out.println("the document stream could not be read");
            e.printStackTrace();
        } catch (SAXException e) {
            //sax contains error of XML parser
            e.printStackTrace();
        } catch (TikaException e) {
            System.out.println("the document could not be parsed");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("can not close input stream ..");
                }
            }
        }
        return results;
    }


    public void extractInFileByTika(InputStream inputStream, String outputFilePath) {

        File outputFile = new File(outputFilePath);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFile);

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

        //Parse context. Used to pass context information to Tika parsers.
        ParseContext pcontext = new ParseContext();

        //Html parser : turn the input document to HTML SAX events
        HtmlParser htmlparser = new HtmlParser();

        try {
            //pcontext take data from inputstream to handler and metadata
            htmlparser.parse(inputStream, handlerFile, metadata, pcontext);

        } catch (IOException e) {
            System.out.println("the document stream could not be read");
            e.printStackTrace();
        } catch (SAXException e) {
            //sax contains error of XML parser
            e.printStackTrace();
        } catch (TikaException e) {
            System.out.println("the document could not be parsed");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("can not close input stream ..");
                }
            }
        }

    }

    public static String extractTextByRegx(String html) {

        String removeTags = html.toString().replaceAll("\\<.*?>", "");

        return removeTags;
    }

    public static List<String> extractText(Reader reader) throws IOException {
//                 reader = new FileReader("filenameSuccess.txt");
        final ArrayList<String> list = new ArrayList<String>();

        ParserDelegator parserDelegator = new ParserDelegator();
        ParserCallback parserCallback = new ParserCallback() {
            public void handleText(final char[] data, final int pos) {
                list.add(new String(data));
            }

            public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) {
            }

            public void handleEndTag(Tag t, final int pos) {
            }

            public void handleSimpleTag(Tag t, MutableAttributeSet a, final int pos) {
            }

            public void handleComment(final char[] data, final int pos) {
            }

            public void handleError(final java.lang.String errMsg, final int pos) {
            }
        };
        parserDelegator.parse(reader, parserCallback, true);
        for (String line : list) {
            System.out.println(line);
        }
        return list;
    }


}
