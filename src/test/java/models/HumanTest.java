package models;

import enums.Country;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanTest {
    @Test
    public void getFirstName() throws Exception {
        Human newHuman = new Human("Name", "Surname", 5000, Country.Brazil, new Club("club", Country.Brazil));
        assertEquals("Name", newHuman.getFirstName());
    }

    @Test
    public void setFirstName() throws Exception {
        Human newHuman = new Human();
        newHuman.setFirstName("Test First Name");
        assertEquals("Test First Name", newHuman.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        Human newHuman = new Human("Name", "Surname", 5000, Country.Brazil, new Club("club", Country.Brazil));
        assertEquals("Surname", newHuman.getLastName());
    }

    @Test
    public void setLastName() throws Exception {
        Human newHuman = new Human();
        newHuman.setLastName("Test Last Name");
        assertEquals("Test Last Name", newHuman.getLastName());
    }

    @Test
    public void getSalary() throws Exception {
        Human newHuman = new Human("Name", "Surname", 5000, Country.Brazil, new Club("club", Country.Brazil));
        assertEquals(5000, newHuman.getSalary());
    }

    @Test
    public void setSalary() throws Exception {
        Human newHuman = new Human();
        newHuman.setSalary(5);
        assertEquals(5, newHuman.getSalary());
    }

    @Test
    public void getCountry() throws Exception {
        Human newHuman = new Human("Name", "Surname", 5000, Country.Brazil, new Club("club", Country.Brazil));
        assertEquals(Country.Brazil, newHuman.getCountry());
    }

    @Test
    public void setCountry() throws Exception {
        Human newHuman = new Human();
        newHuman.setCountry(Country.Ukraine);
        assertEquals(Country.Ukraine, newHuman.getCountry());
    }

    @Test
    public void getClub() throws Exception {
        Club testClub = new Club("club", Country.Brazil);
        Human newHuman = new Human("Name", "Surname", 5000, Country.Brazil, testClub);
        assertEquals(testClub, newHuman.getClub());
    }

    @Test
    public void setClub() throws Exception {
        Human newHuman = new Human();
        Club newClub = new Club();
        newHuman.setClub(newClub);
        assertEquals(newClub,  newHuman.getClub());
    }

}