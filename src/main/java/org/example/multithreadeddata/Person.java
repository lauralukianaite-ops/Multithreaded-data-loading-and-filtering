package org.example.multithreadeddata;

import java.time.LocalDate;

public class Person {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final String country;
    private final String domain;
    private final LocalDate birthDate;

    public Person (long id, String firstName, String lastName, String email, String gender, String country, String domain, LocalDate birthDate){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.domain = domain;
        this.birthDate = birthDate;
    }

    public long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getGender(){
        return gender;
    }

    public String getCountry(){
        return country;
    }

    public String getDomain(){
        return domain;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }
}
