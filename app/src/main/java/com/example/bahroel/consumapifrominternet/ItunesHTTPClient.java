package com.example.bahroel.consumapifrominternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Bahroel on 10/04/2018.
 */

public class ItunesHTTPClient {
    // URL of endpoint API from web Service
    private static String BASE_URL = "https://itunes.apple.com/search?term=jack+johnson";

    public String getItunesStuffData(){
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
           // httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            // readd the response of JSON from URL
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
            }

            inputStream.close();
            httpURLConnection.disconnect();
            return  stringBuffer.toString();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bitmap getBitmapFromURL(String stringUrl){
        Bitmap bitmap = null;

        try {
            URL url = new URL(stringUrl);
            InputStream inputStream = url.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}

