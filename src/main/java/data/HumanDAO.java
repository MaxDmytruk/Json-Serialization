package data;

import models.Human;

import java.util.ArrayList;
import java.util.List;

public class HumanDAO {
    private static List<Human> humans;

    public HumanDAO() {
        humans = new ArrayList<>();
    }

    public List<Human> getHumans(){
        return humans;
    }

    public void addHuman(Human newHuman){
        humans.add(newHuman);
    }

    public void deleteHuman(Human deleteHuman){
        humans.remove(deleteHuman);
    }
}
