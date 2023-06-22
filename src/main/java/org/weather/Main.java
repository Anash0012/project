package org.weather;

import org.json.JSONObject;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String urlString ="https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m";

        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        try {
              url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("Problem in URL");
        }

        try {
            connection= (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("Connection Problem");
        }


        //extract information from connection object;
        if (responseCode==200){
            BufferedReader inp=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apidata=new StringBuilder();
            String readLine=null;
            while((readLine= inp.readLine())!=null){
                apidata.append(readLine);
            }
            inp.close();

            JSONObject jsonAPIResponse=new JSONObject(apidata.toString());
            System.out.println(jsonAPIResponse.get("latitude"));
        }
        else{
            System.out.println("API could not be made!!");
        }
    }
}