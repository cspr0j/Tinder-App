package org.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String email; // login
    private String password;
    private String name;
    private String surname;
//    private String birthDate;
    private Integer age;
    private String gender;

    private boolean isActive;

    public User(String email, String password, String name, String surname, Integer age, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        setGender(gender);
    }

    public Integer getGender() {
        if (this.gender.equals("male")) return 0;
        else return 1;
    }

    public void setGender(String gender) {
        if (gender.equals("0")) this.gender = "male";
        else this.gender = "female";
    }
}
