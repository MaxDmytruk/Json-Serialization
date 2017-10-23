package models;

import enums.Country;

public class Human {
    private String firstName;
    private String lastName;
    private int salary;
    private Country country;
    private Club club;

    public Human() {}

    public Human(String firstName, String lastName, int salary, Country country, Club club) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.country = country;
        this.club = club;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
