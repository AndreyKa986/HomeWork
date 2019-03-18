package lesson16.task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.XML;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class Parse {
    private String string = null;
    String[] arrayOfLink;
    private int number = 0;

    Parse(String[] arrayOfLink) {
        this.arrayOfLink = arrayOfLink;
    }

    synchronized void loading(String loadingString) {
        if (string != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            URL url = new URL(loadingString);
            StringBuilder stringBuilder = new StringBuilder();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    stringBuilder.append(s);
                }
            }
            string = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        notify();
    }

    synchronized void print() {
        if (string == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("\n****** печать строки " + number + " ******\n");
        try {
            gson.fromJson(string, Object.class);
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(string);
            System.out.println(gson.toJson(jsonElement));
        } catch (com.google.gson.JsonSyntaxException ex) {
            JSONObject asd = XML.toJSONObject(string);
            System.out.println(asd.toString(2));
        }
        notify();
        if (number < arrayOfLink.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
