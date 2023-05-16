package com.davisonego.petshop;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    public void Get() {
        new NetworkTask().execute();
    }
}

class NetworkTask extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
        try {
            // create URL object
            URL url = new URL("https://reqres.in/api/users?page=2");

            // create HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            System.out.println("1");

            // read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("2");
            StringBuilder response = new StringBuilder();
            System.out.println("3");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("EVERYLINE");
                System.out.println(line);
                response.append(line);
            }
            reader.close();

            // return response
            return response.toString();
        } catch (Exception e) {
            // handle exception
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // handle result
        if (result != null) {
            System.out.println(result);
        }
    }
}

