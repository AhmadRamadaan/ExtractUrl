package com.principle.network;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UrlManager {


    /**
     * Returns the output from the given URL.
     * <p>
     * I tried to hide some of the ugliness of the exception-handling
     * in this method, and just return a high level Exception from here.
     * Modify this behavior as desired.
     *
     * @param desiredUrl
     * @return
     * @throws Exception
     */
    public static InputStream getConnection(String desiredUrl) {

        //check the content Type of URL(page)
        boolean isHTMLType = new UrlInfoManager().checkHtmlContentType(desiredUrl);
        //if is not html return null
        if (!isHTMLType) {
            System.out.println("Not HTML page");
            System.exit(0);
            return null;

        } else {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            HttpsURLConnection httpsURLConnection = null;
            InputStream inputStream = null;

            try {
                url = new URL(desiredUrl);

                String protocol = url.getProtocol();

                if (protocol.equals("https")) {
                    // create the HttpURLConnection
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    connectHttpUrlConnection(httpURLConnection);
                    inputStream = httpURLConnection.getInputStream();

                } else if (protocol.equals("httpss")) {
                    // create the HttpURLConnection
                    httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    connectHttpsUrlConnection(httpsURLConnection);
                    inputStream = httpsURLConnection.getInputStream();

                } else {
                    inputStream = url.openConnection().getInputStream();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }
    }

    public static void connectHttpUrlConnection(HttpURLConnection httpURLConnection) {


        try {
            // just want to do an HTTP GET here
            httpURLConnection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);

            // give it 15 seconds to respond
            httpURLConnection.setReadTimeout(15 * 1000);
            httpURLConnection.connect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void connectHttpsUrlConnection(HttpsURLConnection httpsURLConnection) {

        try {
            // just want to do an HTTP GET here
            httpsURLConnection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);

            // give it 15 seconds to respond
            httpsURLConnection.setReadTimeout(15 * 1000);
            httpsURLConnection.connect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
