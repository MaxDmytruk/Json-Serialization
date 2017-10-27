package modify_object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Human;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GoogleGSON implements ModifyObject {

    public static Path path = Paths.get("./humansGSON.txt");

    @Override
    public String serialize(List<Human> objectsToSerialize) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(objectsToSerialize);
    }

    @Override
    public List<Human> deserialize() {
        Type itemsListType = new TypeToken<List<Human>>(){}.getType();
        try {
            byte[] byteJson = Files.readAllBytes(path);
            String json = new String(byteJson);
            Gson gson = new Gson();
            List<Human> humans = gson.fromJson(json, itemsListType);
            return humans;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
