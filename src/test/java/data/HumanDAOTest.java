package data;

import models.Human;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class HumanDAOTest {

    private HumanDAO humanDAO = new HumanDAO();

    @Test
    public void getHumans() throws Exception {
        Human first = new Human();
        humanDAO.addHuman(first);
        assertArrayEquals( new Human[]{first}, humanDAO.getHumans().toArray());
    }

    @Test
    public void addHuman() throws Exception {
        Human second = new Human();
        humanDAO.addHuman(second);
        List<Human> humans = humanDAO.getHumans();
        assertEquals(second, humans.get(humans.size() - 1));
    }

    @Test
    public void deleteHuman() throws Exception {
        humanDAO.addHuman(new Human());
        humanDAO.addHuman(new Human());
        humanDAO.addHuman(new Human());
        List<Human> humans = humanDAO.getHumans();
        int beginSize = humans.size();
        Human deletedHuman = humans.get(0);
        humanDAO.deleteHuman(deletedHuman);
        assertEquals(beginSize - 1, humanDAO.getHumans().size());
    }

}