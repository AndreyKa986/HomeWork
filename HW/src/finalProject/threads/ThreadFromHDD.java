package finalProject.threads;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import finalProject.entities.Store;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ThreadFromHDD extends Thread {

    private StoreListener storeListener;

    public ThreadFromHDD(StoreListener storeListener) {
        this.storeListener = storeListener;
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader("test.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type listType = new TypeToken<ArrayList<Store>>() {
        }.getType();
        List<Store> listFromHDD = new Gson().fromJson(builder.toString(), listType);
        System.out.println("Загрузка с жёсткого диска завершена.");
        storeListener.onComplete(listFromHDD);
    }
}
