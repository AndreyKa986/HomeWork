package lesson12;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class WifeDeserializer implements JsonDeserializer<Wife> {
    @Override
    public Wife deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Wife wife = new Wife();
        String data = jsonElement.getAsString();
        String[] strings = data.split(" ");
        wife.name = strings[0];
        wife.age = Integer.parseInt(strings[1]);
        return wife;
    }
}
