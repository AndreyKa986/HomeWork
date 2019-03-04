package lesson12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://goo.gl/Hc8J4n");
        StringBuilder stringBuilder = new StringBuilder();
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
        }
//      Task 1
        ObjectMapper mapper = new ObjectMapper();
        Person[] men = mapper.readValue(stringBuilder.toString(), Person[].class);
//        for(Person i:men)
//            i.print();
        String jsonString = mapper.writeValueAsString(men);
//        System.out.println(jsonString);
//      Task 2
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Wife.class, new WifeSerializer())
                .registerTypeAdapter(Wife.class, new WifeDeserializer())
                .create();
        String jsonString2 = gson.toJson(men);
//        System.out.println(jsonString2);
        Person[] men2 = gson.fromJson(jsonString2, Person[].class);
//        for(Person i:men2)
//            i.print();
//      Task 3
        JSONArray men3 = new JSONArray();
        for (Person person : men2) {
            JSONObject personObject = new JSONObject();
            personObject.put("name", person.name);
            personObject.put("age", person.age);
            personObject.put("isStudent", person.isStudent);
            JSONObject wifeObject = new JSONObject();
            if (person.wife != null) {
                wifeObject.put("name", person.wife.name);
                wifeObject.put("age", person.wife.age);
                personObject.put("wife", wifeObject);
            } else
                personObject.put("wife", JSONObject.NULL);
            JSONArray petArray = new JSONArray();
            if (person.pet != null) {
                for (String personPet : person.pet)
                    petArray.put(personPet);
                personObject.put("pet", petArray);
            } else
                personObject.put("pet", JSONObject.NULL);
            men3.put(personObject);
        }
        String jsonString3 = men3.toString();
//        System.out.println(jsonString3);
    }
}
