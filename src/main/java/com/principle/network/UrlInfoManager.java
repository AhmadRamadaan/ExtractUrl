package com.principle.network;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class UrlInfoManager {

    /**
     * check content of document is it a HTML content or not ?
     *
     * @param url
     * @return true if the content is 'text/html' otherwise return else
     */
    public boolean checkHtmlContentType(String url) {
        URLConnection conn = null;
        try {
            URL url1 = new URL(url);
            conn = url1.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentType contentType = getContentTypeHeader(conn);
        if (!contentType.contentType.equals("text/html"))
            return false; // don't continue if not HTML
        else {
            return true;
        }
    }

    /**
     * Loops through response headers until Content-Type is found.
     *
     * @param conn
     * @return ContentType object representing the value of
     * the Content-Type header
     */
    private static ContentType getContentTypeHeader(URLConnection conn) {
        int i = 0;
        boolean moreHeaders = true;
        do {
            String headerName = conn.getHeaderFieldKey(i);
            String headerValue = conn.getHeaderField(i);
            if (headerName != null && headerName.equals("Content-Type"))
                return new ContentType(headerValue);

            i++;
            moreHeaders = headerName != null || headerValue != null;
        }
        while (moreHeaders);

        return null;
    }

    /**
     * Class holds the content type and charset (if present)
     */
    private static final class ContentType {

        private String contentType;

        //constructor set value of ContentType
        private ContentType(String headerValue) {
            //if header vaue = null
            if (headerValue == null)
                throw new IllegalArgumentException("ContentType must be constructed with a not-null headerValue");

            //if header vallue not equal null
            int n = headerValue.indexOf(";");
            if (n != -1) {
                contentType = headerValue.substring(0, n);

            } else
                contentType = headerValue;
        }
    }

    /**
     * get document of URL to git title from it in the future
     *
     * @param url
     * @return Document
     */
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

    /**
     * get status code of URL page
     *
     * @param url
     * @return
     */

    public int getStatusCode(String url) {

        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).execute();

        } catch (MalformedURLException formedUrl) {
            System.out.println("the request URL is not a HTTP or HTTPS URL, or is otherwise malformed ");
        } catch (HttpStatusException status) {
            System.out.println("page not found");
        } catch (UnsupportedMimeTypeException mime) {
            System.out.println("the response mime type is not supported and those errors are not ignored");
        } catch (SocketTimeoutException socket) {
            System.out.println(" the connection times out");
        } catch (IOException e) {
            System.out.println("can not read from URL 'exception from get stauts code' ");
//            e.printStackTrace();
        }

        int statusCode = response.statusCode();
        return statusCode;
    }

}