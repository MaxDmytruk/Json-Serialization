package modify_object;

import enums.Country;
import models.Club;
import models.Human;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GoogleGSONTest {
    @Test
    public void serialize() throws Exception {
        List<Human> serializeList = new ArrayList<>();
        serializeList.add(new Human("a", "b", 1, Country.Brazil, new Club("c", Country.Brazil)));
        GoogleGSON target = new GoogleGSON();
        String json = target.serialize(serializeList);
        assertTrue(json.length() != 0);
    }

    @Test
    public void deserialize() throws Exception {
        GoogleGSON target = new GoogleGSON();
        List<Human> deserializedHumans = target.deserialize();
        assertTrue(deserializedHumans.size() > 0);
        assertEquals(deserializedHumans.get(0).getFirstName(), "Andrew");
        assertEquals(deserializedHumans.get(0).getLastName(), "Yarmolenko");
        assertEquals(deserializedHumans.get(0).getSalary(), 5000);
        assertEquals(deserializedHumans.get(0).getCountry(), Country.Ukraine);
    }

}