package com.udacity.sandwichclub.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Myriam on 2/20/2018.
 */

public class NetworkUtils {

    public static Bitmap getImageFromHttpUrl(String ImageUrl) throws IOException {
        URL url = null;
        try {
            url = new URL(ImageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.connect();
            InputStream in =  urlConnection.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(in);
            return image;
        } finally {
            urlConnection.disconnect();
        }

    }
}
