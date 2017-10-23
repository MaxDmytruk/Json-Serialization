package core;

import data.HumanDAO;
import models.Human;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void main() throws Exception {
    }

    @Test
    public void bytesToMBytes() throws Exception {
        double result = Main.bytesToMBytes(1048576);
        assertEquals(result, 1, 0);
    }

    @Test
    public void nanoSecondsInSeconds() throws Exception {
        double result = Main.nanoSecondsInSeconds((long)Math.pow(10, 9));
        assertEquals(result, 1 ,0);
    }

    @Test
    public void initWithData() throws Exception {
        HumanDAO humanDAO = new HumanDAO();
        Main.initWithData();
        assertTrue(humanDAO.getHumans().size() > 0);
    }

}