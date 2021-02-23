package com.kevintesar.api;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

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




    public String parseJSONForString(String feedURL)  {

        String result = "";
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


            //Receive the response from the server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
            resultBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                resultBuilder.append(line);
            }

            conn.disconnect();


            return resultBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


}