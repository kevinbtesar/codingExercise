package com.kevintesar.api;

import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JSONParser {

    public JSONObject parseJSON(String feedURL) throws JSONException {
        String result = null;
        StringBuilder resultBuilder;

        try {
            URL urlObj = new URL(feedURL);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            conn.connect();

            try {
                //Receive the response from the server
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                resultBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    resultBuilder.append(line);
                }

                conn.disconnect();


                result = resultBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        if(result != null) {
            return new JSONObject(result);
        }
        return null;
    }

    private boolean isTesting() {
        try {
            Class.forName("com.kevintesar.JSONParserUnitTest");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}