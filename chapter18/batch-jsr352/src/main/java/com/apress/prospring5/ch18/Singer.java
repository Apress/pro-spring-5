package com.apress.prospring5.ch18;

public class Singer {
    private String firstName;
    private String lastName;
    private String song;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName + ", song: " + song;
    }
}
