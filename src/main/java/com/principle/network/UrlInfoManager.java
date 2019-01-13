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

}