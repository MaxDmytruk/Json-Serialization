package modify_object;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Human;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Jackson implements ModifyObject {

    public static Path path = Paths.get("./humansJackson.txt");

    @Override
    public String serialize(List<Human> objectsToSerialize) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringSerialized = new StringWriter();

        try {
            objectMapper.writeValue(stringSerialized, objectsToSerialize);
            return stringSerialized.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Human> deserialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(path);
            List<Human> deserializedObjects = objectMapper.readValue(jsonData, new TypeReference<List<Human>>(){});
            return deserializedObjects;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
