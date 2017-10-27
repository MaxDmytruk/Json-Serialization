package modify_object;

import enums.Country;
import models.Club;
import models.Human;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrgJSON implements ModifyObject {

    public static Path path = Paths.get("./humansJsonORG.txt");

    @Override
    public String serialize(List<Human> humansToSerialize) {
        JSONObject humanListJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            for (int i = 0; i < humansToSerialize.size(); i++){
                JSONObject humanJson = new JSONObject();
                humanJson.put("firstName", humansToSerialize.get(i).getFirstName());
                humanJson.put("lastName", humansToSerialize.get(i).getLastName());
                humanJson.put("country", humansToSerialize.get(i).getCountry());
                humanJson.put("salary", humansToSerialize.get(i).getSalary());
                JSONObject clubJsonObject = new JSONObject();
                clubJsonObject.put("name", humansToSerialize.get(i).getClub().getName());
                clubJsonObject.put("country", humansToSerialize.get(i).getClub().getCountry());
                humanJson.put("club", clubJsonObject);
                jsonArray.put(humanJson);
            }
            humanListJson.put("list", jsonArray);

            return humanListJson.getJSONArray("list").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Human> deserialize() {
        try {
            byte[] byteJson = Files.readAllBytes(path);
            String json = new String(byteJson);
            List<Human> humans = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            jsonArray.getJSONObject(4);
            int humanIndex = 0;
            while(jsonArray.optJSONObject(humanIndex) != null){
                String firstName = (jsonArray.optJSONObject(humanIndex)).getString("firstName");
                String lastName = (jsonArray.optJSONObject(humanIndex)).getString("lastName");
                String country = (jsonArray.optJSONObject(humanIndex)).getString("country");
                int salary = (jsonArray.optJSONObject(humanIndex)).getInt("salary");
                JSONObject club = (jsonArray.optJSONObject(humanIndex)).getJSONObject("club");
                String clubName = club.getString("name");
                String clubCountry = club.getString("country");

                Club newClub = new Club(clubName, Country.valueOf(clubCountry));
                Human human = new Human(firstName, lastName, salary, Country.valueOf(country), newClub);
                humans.add(human);
                humanIndex++;
            }
            return humans;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
