package finalProject.threads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import finalProject.entities.Store;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ThreadFromServer extends Thread {
    private StoreListener storeListener;

    public ThreadFromServer(StoreListener storeListener) {
        this.storeListener = storeListener;
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL("https://vk.com/doc83378370_495634404?hash=76a81aa06e9910e291&dl=6a76fc1896f238fb4e");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    builder.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type listType = new TypeToken<ArrayList<Store>>(){}.getType();
        List<Store> listFromServer = new Gson().fromJson(builder.toString(), listType);
        System.out.println("Загрузка с сервера завершена.");
        storeListener.onComplete(listFromServer);
    }
}
