package models;

import enums.Country;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClubTest {
    @Test
    public void getName() throws Exception {
        Club newClub = new Club("Test Club", Country.Brazil);
        assertEquals("Test Club", newClub.getName());
    }

    @Test
    public void setName() throws Exception {
        Club newClub = new Club();
        newClub.setName("Test");
        assertEquals("Test", newClub.getName());
    }

    @Test
    public void getCountry() throws Exception {
        Club newClub = new Club("Test Club", Country.Brazil);
        assertEquals( Country.Brazil, newClub.getCountry());
    }

    @Test
    public void setCountry() throws Exception {
        Club newClub = new Club();
        newClub.setCountry(Country.Brazil);
        assertEquals(Country.Brazil, newClub.getCountry());
    }

}